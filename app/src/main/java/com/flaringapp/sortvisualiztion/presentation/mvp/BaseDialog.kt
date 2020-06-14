package com.flaringapp.sortvisualiztion.presentation.mvp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.dialogs.blur.BlurDialog

abstract class BaseDialog<T : IBasePresenter<*>> : DialogFragment(), IBaseView {

    abstract val presenter: T

    override val viewContext: Context? get() = context

    private var blurDialog: BlurDialog? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(arguments, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        dialog?.window?.apply {
            val params = attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            attributes = params
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(false)
            window?.requestFeature(Window.FEATURE_NO_TITLE)
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        presenter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setBackgroundResource(R.drawable.bg_dialog)
        blurDialog?.animateAppear()

        onInitPresenter()
        super.onViewCreated(view, savedInstanceState)
        presenter.onStart()
    }

    @CallSuper
    override fun onDestroyView() {
        blurDialog?.requestClose()
        presenter.release()
        super.onDestroyView()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        initBlurDialog(manager.beginTransaction())?.let {
            super.show(it, tag)
        }
    }

    override fun show(transaction: FragmentTransaction, tag: String?): Int {
        return initBlurDialog(transaction)?.let {
            super.show(it, tag)
        } ?: -1
    }

    private fun initBlurDialog(transaction: FragmentTransaction): FragmentTransaction? {
        if (blurDialog != null)
            return null

        blurDialog = BlurDialog.getInstance()
        transaction
            .add(blurDialog!!, BLUR_DIALOG_TAG)

        return transaction
    }

    companion object {
        private const val BLUR_DIALOG_TAG = "dialog_blur"
    }
}