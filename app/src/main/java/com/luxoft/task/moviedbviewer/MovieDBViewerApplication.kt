package com.luxoft.task.moviedbviewer

import android.content.Context
import androidx.multidex.MultiDex
import com.luxoft.task.moviedbviewer.di.DaggerAppComponent
import dagger.android.DaggerApplication

class MovieDBViewerApplication : DaggerApplication() {

    private val appComponent = DaggerAppComponent.factory().create(this)

    override fun applicationInjector() = appComponent

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}