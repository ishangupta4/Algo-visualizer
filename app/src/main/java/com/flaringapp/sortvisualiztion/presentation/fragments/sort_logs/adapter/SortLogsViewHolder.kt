package com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_logs.SortLogsContract
import kotlinx.android.synthetic.main.item_sort_log.view.*

class SortLogsViewHolder private constructor(view: View): RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup): SortLogsViewHolder {
            return SortLogsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sort_log, parent, false)
            )
        }
    }

    fun bind(log: String) {
        itemView.apply {
            logText.text = log
        }
    }

}