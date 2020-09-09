package com.luxoft.task.favourtiesdb.repositories

import io.reactivex.Completable
import io.reactivex.Single

interface FavouritesMovieApi {
    fun addToFavourites(movieId: Int): Completable
    fun removeFromFavourites(movieId: Int): Completable
    fun isMovieFavourite(movieId: Int): Single<Boolean>
}