package com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods

import com.flaringapp.sortvisualiztion.presentation.activities.main.MainContract
import com.flaringapp.sortvisualiztion.presentation.mvp.IBasePresenter
import com.flaringapp.sortvisualiztion.presentation.mvp.IBaseView

interface SortMethodsContract {

    companion object {
        const val DATA_ARRAY_KEY = "key_numbers_array"
    }

    interface ISortMethodModel {
        val nameRes: Int
        val method: SortMethod
    }

    interface ViewContract : IBaseView {
        fun setModels(models: List<ISortMethodModel>)

        fun showSort()
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(appNavigation: MainContract.AppNavigation)

        fun onModelClicked(methodModel: ISortMethodModel)

        fun onSortClicked()
    }

}