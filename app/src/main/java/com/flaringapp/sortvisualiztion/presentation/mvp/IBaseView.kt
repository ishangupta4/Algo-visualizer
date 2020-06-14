package com.flaringapp.sortvisualiztion.presentation.mvp

import android.content.Context
import androidx.annotation.StringRes
import es.dmoral.toasty.Toasty

interface IBaseView {
    val viewContext: Context?

    fun onInitPresenter()

    fun showWarningToast(@StringRes textRes: Int) {
        viewContext?.let { Toasty.warning(it, textRes).show() }
    }

    fun showWarningToast(text: String) {
        viewContext?.let { Toasty.warning(it, text).show() }
    }
}