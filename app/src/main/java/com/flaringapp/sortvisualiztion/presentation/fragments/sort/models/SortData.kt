package com.flaringapp.sortvisualiztion.presentation.fragments.sort.models

import com.flaringapp.sortvisualiztion.presentation.fragments.sort.SortContract
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.SortMethod
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SortData(
    override val methodName: String,
    override val method: SortMethod,
    override val array: IntArray
) : SortContract.ISortData