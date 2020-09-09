package com.luxoft.task.moviedbviewer

import com.luxoft.task.moviedbviewer.di.DaggerAppComponent
import dagger.android.DaggerApplication

class MovieDBViewerApplication : DaggerApplication() {

    private val appComponent = DaggerAppComponent.factory().create(this)

    override fun applicationInjector() = appComponent
}