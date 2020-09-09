package com.luxoft.task.moviedbviewer.detail

import com.luxoft.task.base.rx.ApplicationScheduler
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieApi
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(
    private val scheduler: ApplicationScheduler,
    private val favouritesMovieApi: FavouritesMovieApi
) : MovieDetailContract.Presenter() {

    override fun isFavourite(movieId: Int) {
        scheduler.schedule(
            Single.create<Boolean> { emitter ->
                if (emitter.isDisposed.not()) {
                    emitter.onSuccess(favouritesMovieApi.isMovieFavourite(movieId))
                }
            },
            { view.setFavouriteMovie(it) },
            {},
            this
        )
    }

    override fun addMovieToFavourite(movieId: Int) {
        scheduler.schedule(
            favouritesMovieApi.addToFavourites(movieId),
            { view.setFavouriteMovie(true) },
            {},
            this
        )
    }

    override fun removeMovieFromFavourite(movieId: Int) {
        scheduler.schedule(
            favouritesMovieApi.removeFromFavourites(movieId),
            { view.setFavouriteMovie(false) },
            {},
            this
        )
    }
}