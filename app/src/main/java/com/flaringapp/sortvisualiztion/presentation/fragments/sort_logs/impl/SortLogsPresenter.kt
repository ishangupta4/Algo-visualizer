package com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.impl

import com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.SortLogsContract
import com.flaringapp.sortvisualiztion.presentation.mvp.BasePresenter

class SortLogsPresenter: BasePresenter<SortLogsContract.ViewContract>(),
    SortLogsContract.PresenterContract {

    private var listener: SortLogsContract.SortLoggerParent? = null

    override fun release() {
        listener = null
        super.release()
    }

    override fun init(listener: SortLogsContract.SortLoggerParent) {
        this.listener = listener
    }

    override fun addLog(log: String) {
        view?.addNewLog(log)
    }

    override fun onSortingClicked() {
        listener?.requestClose()
    }

    override fun onBackClicked() {
        listener?.requestClose()
    }

}