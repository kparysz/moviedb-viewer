package com.luxoft.task.favourtiesdb.db

import androidx.room.*
import io.reactivex.Single

@Dao
interface FavouritesMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavourites(entity: FavouritesMovieEntity)

    @Query("DELETE FROM FavouritesMovieEntity WHERE movie_id = :movieId")
    fun removeFavourite(movieId: Int)

    @Query("SELECT EXISTS(SELECT * FROM FavouritesMovieEntity WHERE movie_id = :movieId)")
    @Transaction
    fun isFavourite(movieId: Int): Single<Boolean>
}