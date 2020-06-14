package com.flaringapp.sortvisualiztion.presentation.fragments.sort

import android.os.Parcelable
import androidx.annotation.StringRes
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.SortMethod
import com.flaringapp.sortvisualiztion.presentation.mvp.IBasePresenter
import com.flaringapp.sortvisualiztion.presentation.mvp.IBaseView

interface SortContract {

    companion object {
        const val SORT_DATA_KEY = "key_sort_data"
    }

    interface ISortData: Parcelable {
        val methodName: String
        val method: SortMethod
        val array: IntArray
    }

    interface ViewContract : IBaseView {
        fun updateCaptionText(@StringRes textRes: Int)
        fun updateTimeText(text: String)

        fun setArraySizeText(text: String)
        fun setSortMethodText(text: String)

        fun updateViewSortArray(array: IntArray)

        fun showLogsFragment(logs: List<String>)
        fun hideLogsFragment()

        fun addLog(log: String)
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun onLogsClicked()
        fun requestHideLogs()

        fun onLogAdded(string: String)
    }

}