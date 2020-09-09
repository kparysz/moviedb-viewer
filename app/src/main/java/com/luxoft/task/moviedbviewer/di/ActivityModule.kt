package com.luxoft.task.moviedbviewer.di

import com.luxoft.task.moviedbviewer.detail.MovieDetailActivity
import com.luxoft.task.moviedbviewer.main.MainActivity
import com.luxoft.task.moviedbviewer.nowplaying.NowPlayingMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailActivity(): MovieDetailActivity

    @ContributesAndroidInjector
    abstract fun contributeNowPlayingMoviesFragment(): NowPlayingMoviesFragment
}