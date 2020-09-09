package com.luxoft.task.favourtiesdb.di

import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieApi
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class FavouritesMovieDbModule {

    @Binds
    @Singleton
    abstract fun bindsFavouritesMovieRepository(repository: FavouritesMovieRepository): FavouritesMovieApi
}