package com.flaringapp.sortvisualiztion.presentation.mvp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.flaringapp.sortvisualiztion.presentation.activities.main.BackClickListener

abstract class BaseFragment<T : IBasePresenter<*>> : Fragment(), IBaseView, BackClickListener {

    abstract val presenter: T

    override val viewContext: Context? get() = context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(arguments, savedInstanceState)
    }

    override fun onBackClicked(): Boolean {
        for (fragment in childFragmentManager.fragments.reversed()) {
            if (fragment is BackClickListener) {
                if (fragment.onBackClicked()) {
                    return true
                }
            }
        }

        if (childFragmentManager.backStackEntryCount > 0) {
            childFragmentManager.popBackStack()
            return true
        }

        return false
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        presenter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onInitPresenter()
        super.onViewCreated(view, savedInstanceState)
        presenter.onStart()
    }

    @CallSuper
    override fun onDestroyView() {
        presenter.release()
        super.onDestroyView()
    }
}