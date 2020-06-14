package com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.activities.main.BackClickListener
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.SortLogsContract
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.adapter.SortLogsAdapter
import com.flaringapp.sortvisualiztion.presentation.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_sort_logs.*
import org.koin.androidx.scope.currentScope

class SortLogsFragment : BaseFragment<SortLogsContract.PresenterContract>(),
    SortLogsContract.ViewContract, SortLogsContract.SortLogger, BackClickListener {

    override val presenter: SortLogsContract.PresenterContract by currentScope.inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort_logs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitPresenter() {
        presenter.view = this
        presenter.init(
            parentFragment as SortLogsContract.SortLoggerParent
        )
    }

    override fun initLogs(logs: List<String>) {
        (logsRecycler.adapter as SortLogsAdapter).setModels(logs)
    }

    override fun addNewLog(log: String) {
        logsRecycler?.apply {
            post {
                (adapter as SortLogsAdapter).addModel(log)
                val lastVisiblePosition =
                    (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val itemsCount = adapter!!.itemCount
                if (lastVisiblePosition >= itemsCount - 1 - SCROLL_ITEM_TOLERANCE) {
                    smoothScrollToPosition(itemsCount - 1)
                }
            }
        }
    }

    override fun addLog(log: String) {
        presenter.addLog(log)
    }

    private fun initViews() {
        sortingButton.setOnClickListener { presenter.onSortingClicked() }

        logsRecycler.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false).apply {
                stackFromEnd = true
            }
            adapter = SortLogsAdapter(
                arguments!!.getStringArray(LOGS_KEY)!!.toList()
            )
        }
    }

    companion object {
        private const val LOGS_KEY = "key_logs"

        private const val SCROLL_ITEM_TOLERANCE = 1

        fun newInstance(logs: List<String>): SortLogsFragment {
            return SortLogsFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(LOGS_KEY, logs.toTypedArray())
                }
            }
        }
    }
}