package com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SortLogsAdapter(
    initialLogs: List<String>
) : RecyclerView.Adapter<SortLogsViewHolder>() {
    private val logs = ArrayList<String>(initialLogs)

    override fun getItemCount() = logs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortLogsViewHolder {
        return SortLogsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SortLogsViewHolder, position: Int) {
        holder.bind(logs[position])
    }

    fun setModels(logs: List<String>) {
        this.logs.apply {
            clear()
            addAll(logs)
        }
        notifyDataSetChanged()
    }

    fun addModel(log: String) {
        logs += log
        notifyItemInserted(logs.size - 1)
    }
}