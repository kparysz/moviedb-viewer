package com.luxoft.task.base

internal interface MvpPresenter<V> {

    fun attachView(view: V)

    fun detachView()
}