package com.luxoft.task.favourtiesdb.db

import androidx.room.*

@Dao
interface FavouritesMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavourites(entity: FavouritesMovieEntity)

    @Delete
    fun removeFavourite(entity: FavouritesMovieEntity)

    @Query("SELECT EXISTS(SELECT * FROM FavouritesMovieEntity WHERE movie_id = :movieId)")
    @Transaction
    fun isFavourite(movieId: Int): Boolean
}