package com.flaringapp.sortvisualiztion.presentation.fragments.intro.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.activities.main.MainContract
import com.flaringapp.sortvisualiztion.presentation.fragments.intro.IntroContract
import com.flaringapp.sortvisualiztion.presentation.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_intro.*
import org.koin.androidx.scope.currentScope

class IntroFragment: BaseFragment<IntroContract.PresenterContract>(), IntroContract.ViewContract {

    override val presenter: IntroContract.PresenterContract by currentScope.inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitPresenter() {
        presenter.view = this
        presenter.init(activity as MainContract.AppNavigation)
    }

    override fun updateSortAnimation(array: IntArray) {
        sortView?.invalidateNumbers(array)
    }

    private fun initViews() {
        buttonStart.setOnClickListener { presenter.onStartClicked() }

        sortView.minHeight = 0.2f
    }

    companion object {
        fun newInstance() = IntroFragment()
    }
}