package com.example.domain.usecase.base

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

abstract class DefaultSingleObserver<T> : SingleObserver<T> {
    override fun onSuccess(t: T) {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}