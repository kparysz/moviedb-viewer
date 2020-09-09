package com.luxoft.task.nowplaying.di

import com.luxoft.task.nowplaying.presenter.NowPlayingContract
import com.luxoft.task.nowplaying.presenter.NowPlayingPresenter
import com.luxoft.task.nowplaying.repository.NowPlayingApi
import com.luxoft.task.nowplaying.repository.NowPlayingRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NowPlayingModule {

    @Binds
    @Singleton
    abstract fun bindsNowPlayingPresenter(presenter: NowPlayingPresenter): NowPlayingContract.Presenter

    @Binds
    @Singleton
    abstract fun bindsNowPlayingRepository(repository: NowPlayingRepository): NowPlayingApi
}