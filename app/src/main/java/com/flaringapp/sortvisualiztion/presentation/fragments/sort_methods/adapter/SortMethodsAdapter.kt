package com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.SortMethodsContract

class SortMethodsAdapter(
    private val clickBlock: (model: SortMethodsContract.ISortMethodModel) -> Unit
) : RecyclerView.Adapter<SortMethodViewHolder>() {

    var models: List<SortMethodsContract.ISortMethodModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var selectedPos = -1

    override fun getItemCount() = models.size

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) SortMethodViewType.LEFT
        else SortMethodViewType.RIGHT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortMethodViewHolder {
        return SortMethodViewHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: SortMethodViewHolder, position: Int) {
        holder.bind(models[position], selectedPos == position) {
            if (selectedPos != position) {
                val lastSelectedPos = selectedPos
                selectedPos = position
                notifyItemChanged(selectedPos)
                notifyItemChanged(lastSelectedPos)

                clickBlock(models[position])
            }
        }
    }

    override fun onViewRecycled(holder: SortMethodViewHolder) {
        holder.unbind()
        super.onViewRecycled(holder)
    }
}