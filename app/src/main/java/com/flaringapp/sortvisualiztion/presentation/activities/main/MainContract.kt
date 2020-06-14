package com.flaringapp.sortvisualiztion.presentation.activities.main

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.activities.main.navigation.Screen
import com.flaringapp.sortvisualiztion.presentation.mvp.IBasePresenter
import com.flaringapp.sortvisualiztion.presentation.mvp.IBaseView

interface MainContract {

    interface ViewContract : IBaseView

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(fragmentManager: FragmentManager)
        fun onNavigationRequested(
            screen: Screen,
            data: Any? = null,
            @AnimRes @AnimatorRes
            inAnim: Int,
            @AnimRes @AnimatorRes
            outAnim: Int,
            @AnimRes @AnimatorRes
            popInAnim: Int,
            @AnimRes @AnimatorRes
            popOutAnim: Int
        ): Fragment
    }

    interface AppNavigation {
        fun openScreen(
            screen: Screen,
            data: Any? = null,
            @AnimRes @AnimatorRes
            inAnim: Int = R.anim.fragment_appear_from_right,
            @AnimRes @AnimatorRes
            outAnim: Int = R.anim.fragment_disappear_to_left,
            @AnimRes @AnimatorRes
            popInAnim: Int = R.anim.fragment_appear_from_left,
            @AnimRes @AnimatorRes
            popOutAnim: Int = R.anim.fragment_disappear_to_right
        ): Fragment
    }

}