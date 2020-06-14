package com.flaringapp.sortvisualiztion.presentation.activities.main.impl

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.activities.main.MainContract
import com.flaringapp.sortvisualiztion.presentation.activities.main.navigation.Screen
import com.flaringapp.sortvisualiztion.presentation.fragments.create_array.impl.CreateArrayFragment
import com.flaringapp.sortvisualiztion.presentation.fragments.intro.impl.IntroFragment
import com.flaringapp.sortvisualiztion.presentation.fragments.sort.SortContract
import com.flaringapp.sortvisualiztion.presentation.fragments.sort.impl.SortFragment
import com.flaringapp.sortvisualiztion.presentation.fragments.sort_methods.impl.SortMethodsFragment
import com.flaringapp.sortvisualiztion.presentation.mvp.BasePresenter

class MainPresenter : BasePresenter<MainContract.ViewContract>(),
    MainContract.PresenterContract {

    private var fragmentManager: FragmentManager? = null

    override fun release() {
        fragmentManager = null
        super.release()
    }

    override fun init(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    override fun onNavigationRequested(
        screen: Screen,
        data: Any?,
        inAnim: Int,
        outAnim: Int,
        popInAnim: Int,
        popOutAnim: Int
    ): Fragment {
        return when (screen) {
            Screen.INTRO -> IntroFragment.newInstance()
            Screen.CREATE_ARRAY -> CreateArrayFragment.newInstance()
            Screen.SORT_METHODS -> SortMethodsFragment.newInstance(data!! as IntArray)
            Screen.SORT -> SortFragment.newInstance(data!! as SortContract.ISortData)
        }.also {
            fragmentManager?.commit {
                setCustomAnimations(inAnim, outAnim, popInAnim, popOutAnim)
                replace(R.id.fragmentContainer, it)
                addToBackStack(null)
            }
        }
    }
}