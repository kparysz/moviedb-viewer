package com.luxoft.task.nowplaying.presenter

import com.luxoft.task.base.rx.ApplicationScheduler
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieApi
import com.luxoft.task.nowplaying.repository.NowPlayingApi
import javax.inject.Inject

class NowPlayingPresenter @Inject constructor(
    private val scheduler: ApplicationScheduler,
    private val nowPlayingApi: NowPlayingApi,
    private val favouritesMovieApi: FavouritesMovieApi
) : NowPlayingContract.Presenter() {

    override fun getNowPlayingMovies() {
        scheduler.schedule(
            nowPlayingApi.getNowPlaying(),
            {
                view.showNowPlayingMovies(it)
            },
            { },
            this
        )
    }

    override fun addMovieToFavourite(movieId: Int) {
        favouritesMovieApi.addToFavourites(movieId)
    }

    override fun removeMovieFromFavourite(movieId: Int) {
        favouritesMovieApi.removeFromFavourites(movieId)
    }
}