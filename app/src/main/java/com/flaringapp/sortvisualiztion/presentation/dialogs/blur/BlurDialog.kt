package com.flaringapp.sortvisualiztion.presentation.dialogs.blur

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.utils.view.ViewUtils
import kotlinx.android.synthetic.main.dialog_blur.*

class BlurDialog : DialogFragment() {

    private var waitingToShow = false
    private var waitingToClose = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogWithFadeStyle)
    }

    override fun onResume() {
        super.onResume()

        if(waitingToClose) dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.window?.apply {
            setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
            clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_blur, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blur_view.alpha = 0f

        if (waitingToShow) animateAppear()
    }

    fun requestClose() {
        if (fragmentManager != null && isAdded && isVisible && isResumed)
            try { super.dismiss() } catch (e: Exception) {}

        else waitingToClose = true
    }

    fun animateAppear() {
        if (blur_view != null) {
            ViewUtils.animateAppear(blur_view)
        }
        else waitingToShow = true
    }

    fun animateHide() {
        if (blur_view != null) {
            ViewUtils.animateHide(blur_view)
        }
        else waitingToShow = true
    }

    companion object {
        fun getInstance(): BlurDialog {
            return BlurDialog()
        }
    }
}