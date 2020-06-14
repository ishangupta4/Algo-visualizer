package com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.models

import androidx.annotation.StringRes
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.SortMethod
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.SortMethodsContract

data class SortMethodModel(
    @StringRes
    override val nameRes: Int,
    override val method: SortMethod
) : SortMethodsContract.ISortMethodModel