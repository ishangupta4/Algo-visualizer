package com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit

import com.flaringapp.sortvisualiztion.presentation.mvp.IBasePresenter
import com.flaringapp.sortvisualiztion.presentation.mvp.IBaseView

interface ArrayEditContract {

    companion object {
        const val DATA_ARRAY_KEY = "key_data_array"
    }

    interface ViewContract: IBaseView {
        fun initText(text: String)
        fun addText(text: String)

        fun clearInput()

        fun close()
    }

    interface PresenterContract: IBasePresenter<ViewContract> {
        fun init(listener: ArrayEditParent)

        fun onAddPressed(text: String)

        fun onSavePressed()
        fun onCancelPressed()
    }


    interface ArrayEditParent {
        fun onArrayEdited(array: IntArray)
    }
}