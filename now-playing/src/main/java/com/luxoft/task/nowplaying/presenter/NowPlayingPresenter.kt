package com.luxoft.task.nowplaying.presenter

import com.luxoft.task.base.rx.ApplicationScheduler
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieApi
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
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
                mapWithAction(it)
                view.showNowPlayingMovies(it)
                view.fillAutoCompleteAdapter(it)
            },
            { view.showError() },
            this
        )
    }

    private fun mapWithAction(it: List<NowPlayingMovieViewData>) {
        it.map {
            it.apply {
                favouriteAction = if (isLiked) {
                    { removeMovieFromFavourite(this.id) }
                } else {
                    { addMovieToFavourite(this.id) }
                }
            }
        }
    }

    private fun addMovieToFavourite(movieId: Int) {
        scheduler.schedule(
            favouritesMovieApi.addToFavourites(movieId),
            { view.refresh() },
            { view.showError() },
            this
        )
    }

    private fun removeMovieFromFavourite(movieId: Int) {
        scheduler.schedule(
            favouritesMovieApi.removeFromFavourites(movieId),
            { view.refresh() },
            { view.showError() },
            this
        )
    }
}