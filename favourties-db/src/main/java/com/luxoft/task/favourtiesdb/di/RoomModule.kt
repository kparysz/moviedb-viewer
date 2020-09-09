package com.luxoft.task.favourtiesdb.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.luxoft.task.favourtiesdb.db.FavouritesMovieDao
import com.luxoft.task.favourtiesdb.db.FavouritesMovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(@NonNull application: Application): FavouritesMovieDatabase =
        Room.databaseBuilder(
            application,
            FavouritesMovieDatabase::class.java,
            "fav-db"
        )
            .build()

    @Singleton
    @Provides
    fun providesFavouritesMovieDao(favouritesMovieDatabase: FavouritesMovieDatabase): FavouritesMovieDao =
        favouritesMovieDatabase.favouritesDao
}