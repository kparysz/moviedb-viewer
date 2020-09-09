package com.luxoft.task.search.di

import com.luxoft.task.search.repository.SearchMovieApi
import com.luxoft.task.search.repository.SearchRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SearchModule {

//    @Binds
//    abstract fun bindsNowPlayingPresenter(presenter: NowPlayingPresenter): NowPlayingContract.Presenter

    @Binds
    @Singleton
    abstract fun bindsNowPlayingRepository(repository: SearchRepository): SearchMovieApi
}