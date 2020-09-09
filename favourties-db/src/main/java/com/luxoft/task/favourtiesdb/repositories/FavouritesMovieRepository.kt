package com.luxoft.task.favourtiesdb.repositories

import com.luxoft.task.favourtiesdb.db.FavouritesMovieDao
import com.luxoft.task.favourtiesdb.db.FavouritesMovieEntity
import io.reactivex.Completable
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
            favouritesDao.removeFavourite(FavouritesMovieEntity(movieId))
        }

    override fun isMovieFavourite(movieId: Int): Boolean {
        return favouritesDao.isFavourite(movieId)
    }
}