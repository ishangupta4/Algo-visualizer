package com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.impl

import android.os.Bundle
import android.view.*
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.ArrayEditContract
import com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.ArrayEditContract.Companion.DATA_ARRAY_KEY
import com.flaringapp.sortvisualiztion.presentation.mvp.BaseDialog
import kotlinx.android.synthetic.main.dialog_edit_array.*
import org.koin.androidx.scope.currentScope
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class ArrayEditDialog: BaseDialog<ArrayEditContract.PresenterContract>(),
    ArrayEditContract.ViewContract {

    override val presenter: ArrayEditContract.PresenterContract by currentScope.inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_edit_array, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitPresenter() {
        presenter.view = this
        presenter.init(parentFragment as ArrayEditContract.ArrayEditParent)
    }

    override fun initText(text: String) {
        arrayText?.text = text
    }

    override fun addText(text: String) {
        arrayText?.apply { append(text) }
        arrayScrollView.fullScroll(View.FOCUS_DOWN)
    }

    override fun clearInput() {
        numberInput?.setText("")
    }

    override fun close() {
        dismiss()
    }

    private fun initViews() {
        buttonAdd.setOnClickListener { presenter.onAddPressed(numberInput.text.toString()) }

        buttonSave.setOnClickListener { presenter.onSavePressed() }
        buttonCancel.setOnClickListener { presenter.onCancelPressed() }

        numberInput.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                presenter.onAddPressed(numberInput.text.toString())
                return@setOnKeyListener true
            }
            false
        }

        numberInput.post {
            numberInput?.requestFocus()
            (context!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(numberInput, 0)
        }
    }

    companion object {
        fun newInstance(array: IntArray) : ArrayEditDialog {
            return ArrayEditDialog().apply {
                arguments = Bundle().apply {
                    putIntArray(DATA_ARRAY_KEY, array)
                }
            }
        }
    }

}