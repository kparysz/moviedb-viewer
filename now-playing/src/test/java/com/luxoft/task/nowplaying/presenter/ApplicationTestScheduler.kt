package com.luxoft.task.nowplaying.presenter

import com.luxoft.task.base.rx.AndroidScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ApplicationTestScheduler @JvmOverloads constructor(
    private val observing: Scheduler = Schedulers.trampoline(),
    private val executing: Scheduler = Schedulers.trampoline()
) : AndroidScheduler(observing, executing)