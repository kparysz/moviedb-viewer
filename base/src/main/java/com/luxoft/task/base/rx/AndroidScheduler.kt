package com.luxoft.task.base.rx

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

open class AndroidScheduler @Inject constructor(
    private val executingScheduler: Scheduler,
    private val observingScheduler: Scheduler
) : ApplicationScheduler {

    private val subscriptions = HashMap<String, MutableList<Disposable>>()

    override fun <C> schedule(
        single: Single<C>,
        onNextAction: (C) -> Unit,
        onErrorAction: (Throwable) -> Unit,
        subscriber: Any
    ) {
        getSubscriptions(subscriber).add(
            single
                .onTerminateDetach()
                .observeOn(observingScheduler)
                .subscribeOn(executingScheduler)
                .subscribe(onNextAction, onErrorAction)
        )
    }

    override fun disposeFor(subscriber: Any): Int {
        val tag = getSubscriberTag(subscriber)

        if (!subscriptions.containsKey(tag)) {
            return 0
        }

        val taggedSubscriptions =
            if (this.subscriptions.containsKey(tag))
                this.subscriptions[tag]!!
            else
                mutableListOf()

        val numberOfSubscriptions = taggedSubscriptions.size
        for (subscription in taggedSubscriptions) {
            subscription.dispose()
        }
        subscriptions.remove(tag)
        return numberOfSubscriptions
    }

    override fun subscribeOnScheduler(): Scheduler {
        return executingScheduler
    }

    override fun observeOnScheduler(): Scheduler {
        return observingScheduler
    }

    private fun getSubscriberTag(subscriber: Any) =
        if (subscriber is String)
            subscriber
        else
            subscriber.javaClass.toString() + System.identityHashCode(subscriber).toString()

    private fun getSubscriptions(subscriber: Any): MutableList<Disposable> {
        val tag = getSubscriberTag(subscriber)
        return subscriptions[tag].takeUnless { it == null }
            ?: ArrayList<Disposable>().also {
                subscriptions[tag] = it
            }
    }
}

