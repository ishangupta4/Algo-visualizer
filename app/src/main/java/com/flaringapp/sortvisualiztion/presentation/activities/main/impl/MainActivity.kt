package com.flaringapp.sortvisualiztion.presentation.activities.main.impl

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.flaringapp.sortvisualiztion.R
import com.flaringapp.sortvisualiztion.presentation.activities.main.BackClickListener
import com.flaringapp.sortvisualiztion.presentation.activities.main.MainContract
import com.flaringapp.sortvisualiztion.presentation.activities.main.navigation.Screen
import com.flaringapp.sortvisualiztion.presentation.fragments.intro.impl.IntroFragment
import com.flaringapp.sortvisualiztion.presentation.mvp.BaseActivity
import org.koin.androidx.scope.currentScope

class MainActivity : BaseActivity<MainContract.PresenterContract>(), MainContract.ViewContract,
    MainContract.AppNavigation {

    override val presenter: MainContract.PresenterContract by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onInitPresenter()
        presenter.onStart()

        supportFragmentManager.commit {
            add(R.id.fragmentContainer, IntroFragment.newInstance())
        }
    }

    override fun onInitPresenter() {
        presenter.view = this
        presenter.init(supportFragmentManager)
    }

    override fun openScreen(
        screen: Screen,
        data: Any?,
        inAnim: Int,
        outAnim: Int,
        popInAnim: Int,
        popOutAnim: Int
    ): Fragment {
        return presenter.onNavigationRequested(screen, data, inAnim, outAnim, popInAnim, popOutAnim)
    }
}
