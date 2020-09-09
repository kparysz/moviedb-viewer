package com.luxoft.task.base

import com.luxoft.task.base.rx.AndroidScheduler
import com.luxoft.task.base.rx.ApplicationScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class RxExtensionModule {

    @Provides
    @Singleton
    fun bindsAndroidScheduler(): ApplicationScheduler =
        AndroidScheduler(Schedulers.io(), AndroidSchedulers.mainThread())
}