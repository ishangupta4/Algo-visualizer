package com.flaringapp.sortvisualiztion.presentation.fragments.create_array

import com.flaringapp.sortvisualiztion.presentation.activities.main.MainContract
import com.flaringapp.sortvisualiztion.presentation.dialogs.array_edit.impl.ArrayEditDialog
import com.flaringapp.sortvisualiztion.presentation.mvp.IBasePresenter
import com.flaringapp.sortvisualiztion.presentation.mvp.IBaseView

interface CreateArrayContract {

    interface ViewContract : IBaseView {
        fun updateArrayText(text: String)

        fun showArrayEditDialog(dialog: ArrayEditDialog)
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(appNavigation: MainContract.AppNavigation)

        fun onEditClicked()
        fun onRandomClicked()

        fun onArrayEdited(array: IntArray)

        fun onContinueClicked()
    }

}