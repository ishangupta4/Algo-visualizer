package com.flaringapp.sortvisualiztion.utils

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

object RxUtils {

    fun <T> createDelayedFlowable(publishSubject: PublishSubject<T>, delay: Long): Flowable<T> {
        var lastUpdateTime = 0L

        return publishSubject
            .toFlowable(BackpressureStrategy.LATEST)
            .flatMap {
                if (System.currentTimeMillis() - lastUpdateTime >= delay) Flowable.just(it).also {
                    lastUpdateTime = System.currentTimeMillis()
                }
                else Flowable.empty()
            }
            .onApiThread()
    }

}

fun <T> Single<T>.onApiThread() = this
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

fun <T> Single<T>.observeOnUI() = this
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.onApiThread() = this
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

fun <T> Observable<T>.observeOnUI() = this
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.onApiThread() = this
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

fun <T> Flowable<T>.onComputationThread(): Flowable<T> = this
    .subscribeOn(Schedulers.computation())
    .observeOn(Schedulers.computation())

fun <T> Flowable<T>.observeOnUI() = this
    .observeOn(AndroidSchedulers.mainThread())

fun <T> PublishSubject<T>.onApiThread() = this
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

fun <T> PublishSubject<T>.observeOnUI() = this
    .observeOn(AndroidSchedulers.mainThread())