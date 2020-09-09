package com.luxoft.task.base.rx

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single

interface ApplicationScheduler {

    fun <C> schedule(
        single: Single<C>,
        onNextAction: (C) -> Unit,
        onErrorAction: (Throwable) -> Unit,
        subscriber: Any
    )

    fun schedule(
        completable: Completable,
        onNextAction: () -> Unit,
        onErrorAction: (Throwable) -> Unit,
        subscriber: Any
    )

    fun disposeFor(subscriber: Any): Int

    fun subscribeOnScheduler(): Scheduler

    fun observeOnScheduler(): Scheduler
}
