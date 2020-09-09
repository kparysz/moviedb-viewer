package com.luxoft.task.moviedbviewer.di

import android.app.Application
import com.luxoft.task.base.RxExtensionModule
import com.luxoft.task.base.di.NetworkModule
import com.luxoft.task.favourtiesdb.di.FavouritesMovieDbModule
import com.luxoft.task.favourtiesdb.di.RoomModule
import com.luxoft.task.nowplaying.di.MoviesRetrofitModule
import com.luxoft.task.nowplaying.di.NowPlayingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        NetworkModule::class,
        RxExtensionModule::class,
        NowPlayingModule::class,
        FavouritesMovieDbModule::class,
        RoomModule::class,
        MoviesRetrofitModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
