package com.luxoft.task.moviedbviewer.di

import com.luxoft.task.moviedbviewer.detail.MovieDetailContract
import com.luxoft.task.moviedbviewer.detail.MovieDetailPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class DetailModule {

    @Binds
    abstract fun bindsMovieDetailPresenter(presenter: MovieDetailPresenter): MovieDetailContract.Presenter
}