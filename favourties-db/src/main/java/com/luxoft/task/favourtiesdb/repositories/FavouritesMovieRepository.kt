package com.luxoft.task.favourtiesdb.repositories

import com.luxoft.task.favourtiesdb.db.FavouritesMovieDao
import com.luxoft.task.favourtiesdb.db.FavouritesMovieEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavouritesMovieRepository @Inject constructor(
    private val favouritesDao: FavouritesMovieDao
) : FavouritesMovieApi {

    override fun addToFavourites(movieId: Int): Completable =
        Completable.fromAction {
            favouritesDao.addToFavourites(FavouritesMovieEntity(movieId))
        }

    override fun removeFromFavourites(movieId: Int): Completable =
        Completable.fromAction {
            favouritesDao.removeFavourite(movieId)
        }

    override fun isMovieFavourite(movieId: Int): Single<Boolean> =
        favouritesDao.isFavourite(movieId)
}