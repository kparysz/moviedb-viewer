package com.luxoft.task.search.di

import com.luxoft.task.search.presenter.SearchContract
import com.luxoft.task.search.presenter.SearchPresenter
import com.luxoft.task.search.repository.SearchMovieApi
import com.luxoft.task.search.repository.SearchRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SearchModule {

    @Binds
    abstract fun bindsSearchPresenter(presenter: SearchPresenter): SearchContract.Presenter

    @Binds
    @Singleton
    abstract fun bindsNowPlayingRepository(repository: SearchRepository): SearchMovieApi
}