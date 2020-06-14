package com.flaringapp.sortvisualiztion.presentation.mvp

import android.os.Bundle

interface IBasePresenter<T : IBaseView> {

    var view: T?

    fun onCreate(arguments: Bundle?, savedInstanceState: Bundle?)
    fun saveInstanceState(outState: Bundle)

    fun onStart()

    fun release()
}