package com.luxoft.task.favourtiesdb.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouritesMovieEntity::class], version = 1, exportSchema = false)
abstract class FavouritesMovieDatabase : RoomDatabase() {
    abstract val favouritesDao: FavouritesMovieDao
}
