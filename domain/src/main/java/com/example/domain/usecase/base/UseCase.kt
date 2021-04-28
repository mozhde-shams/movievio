package com.example.domain.usecase.base

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver

abstract class UseCase<T, Params>(
    private val executorThread: Scheduler,
    private val uiThread: Scheduler
) {
    abstract fun buildUseCaseObservable(params: Params): Single<T>

    fun setScheduler(params: Params): Single<T> {
        return buildUseCaseObservable(params).subscribeOn(executorThread).observeOn(uiThread)
    }

    fun execute(observer: SingleObserver<T>, params: Params): SingleObserver<T>? {
        return setScheduler(params).subscribeWith(observer)
    }
}