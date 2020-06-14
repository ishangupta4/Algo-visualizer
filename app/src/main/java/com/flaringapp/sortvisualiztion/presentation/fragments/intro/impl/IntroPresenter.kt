package com.flaringapp.sortvisualiztion.presentation.fragments.intro.impl

import com.flaringapp.sortvisualiztion.presentation.activities.main.MainContract
import com.flaringapp.sortvisualiztion.presentation.activities.main.navigation.Screen
import com.flaringapp.sortvisualiztion.presentation.fragments.intro.IntroContract
import com.flaringapp.sortvisualiztion.presentation.mvp.BasePresenter
import com.flaringapp.sortvisualiztion.utils.swap
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class IntroPresenter : BasePresenter<IntroContract.ViewContract>(),
    IntroContract.PresenterContract {

    companion object {
        private const val ANIMATION_ITEMS_COUNT = 20
        private const val ANIMATION_DELAY = 100L
    }

    private var appNavigation: MainContract.AppNavigation? = null

    private var lastAnimatedPosition = -1
    private var animationDisposable: Disposable? = null

    override fun init(appNavigation: MainContract.AppNavigation) {
        this.appNavigation = appNavigation
    }

    override fun onStart() {
        super.onStart()
        initArrayAnimation()
    }

    override fun release() {
        appNavigation = null
        animationDisposable?.dispose()
        super.release()
    }

    override fun onStartClicked() {
        appNavigation?.openScreen(Screen.CREATE_ARRAY)
    }

    private fun initArrayAnimation() {
        val array = IntArray(ANIMATION_ITEMS_COUNT) { (5..15).random() }

        animationDisposable = Observable.interval(ANIMATION_DELAY, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                var first = (array.indices).random()

                if (first == lastAnimatedPosition) {
                    if (first == array.size - 1) first--
                    else first++
                }

                val second = when (first) {
                    0 -> 1
                    array.size - 1 -> array.size - 2
                    else -> {
                        if ((0..1).random() == 0) first - 1
                        else first + 1
                    }
                }
                array.swap(first, second)

                lastAnimatedPosition = first
            }
            .subscribe { view?.updateSortAnimation(array) }
    }
}