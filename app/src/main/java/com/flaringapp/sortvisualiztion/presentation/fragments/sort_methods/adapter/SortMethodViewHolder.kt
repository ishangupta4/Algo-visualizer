package com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.SortMethodsContract
import kotlinx.android.synthetic.main.item_sort_method_left.view.*

class SortMethodViewHolder private constructor(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup, viewType: Int): SortMethodViewHolder {
            return SortMethodViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(
                        if (viewType == SortMethodViewType.LEFT) {
                            R.layout.item_sort_method_left
                        } else {
                            R.layout.item_sort_method_right
                        },
                        parent,
                        false
                    )
            )
        }
    }

    fun bind(
        model: SortMethodsContract.ISortMethodModel,
        isSelected: Boolean,
        clickBlock: () -> Unit
    ) {
        itemView.button.apply {
            setText(model.nameRes)
            setOnClickListener { clickBlock() }

            if (isSelected) {
                background.setTint(ContextCompat.getColor(context, R.color.buttonSelectedColor))
                setTextColor(ContextCompat.getColor(context, R.color.buttonSelectedTextColor))
            } else {
                background.setTint(ContextCompat.getColor(context, R.color.buttonColor))
                setTextColor(ContextCompat.getColor(context, R.color.buttonTextColor))
            }
        }
    }

    fun unbind() {
        itemView.button.setOnClickListener(null)
    }
}