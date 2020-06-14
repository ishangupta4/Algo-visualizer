package com.flaringapp.sortvisualiztion.utils

import android.content.Context
import android.os.Build
import java.util.*

object AndroidUtils {
}

fun Context.getCurrentLocale(): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
        resources.configuration.locales.get(0)
    } else{
        @Suppress("DEPRECATION")
        resources.configuration.locale
    }
}