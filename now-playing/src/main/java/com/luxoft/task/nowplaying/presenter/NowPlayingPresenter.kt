package com.luxoft.task.nowplaying.presenter

import com.luxoft.task.base.rx.ApplicationScheduler
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieApi
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.nowplaying.repository.NowPlayingApi
import com.luxoft.task.search.repository.SearchMovieApi
import javax.inject.Inject

class NowPlayingPresenter @Inject constructor(
    private val scheduler: ApplicationScheduler,
    private val nowPlayingApi: NowPlayingApi,
    private val searchMovieApi: SearchMovieApi,
    private val favouritesMovieApi: FavouritesMovieApi
) : NowPlayingContract.Presenter() {

    override fun getNowPlayingMovies() {
        scheduler.schedule(
            nowPlayingApi.getNowPlaying(),
            {
                localNowPlayingMovies = mapWithAction(it)
                view.showNowPlayingMovies(it)
                view.fillAutoCompleteAdapter(it)
            },
            { view.showError() },
            this
        )
    }

    override fun findMovie(query: String) {
        scheduler.schedule(
            searchMovieApi.searchMovie(query),
            { },
            { view.showError() },
            this
        )
    }

    private fun mapWithAction(nowPlayingMovies: List<NowPlayingMovieViewData>) =
        nowPlayingMovies.map { nowPlayingMovie ->
            nowPlayingMovie.apply {
                favouriteAction = if (isLiked) {
                    { removeMovieFromFavourite(this.id) }
                } else {
                    { addMovieToFavourite(this.id) }
                }
            }
        }

    private fun addMovieToFavourite(movieId: Int) {
        scheduler.schedule(
            favouritesMovieApi.addToFavourites(movieId),
            {
                setFavouriteMovie(movieId, true)
                view.refresh(localNowPlayingMovies)
            },
            { view.showError() },
            this
        )
    }

    private fun removeMovieFromFavourite(movieId: Int) {
        scheduler.schedule(
            favouritesMovieApi.removeFromFavourites(movieId),
            {
                setFavouriteMovie(movieId, false)
                view.refresh(localNowPlayingMovies)
            },
            { view.showError() },
            this
        )
    }

    private fun setFavouriteMovie(movieId: Int, isLiked: Boolean) {
        localNowPlayingMovies.first { it.id == movieId }.isLiked = isLiked
    }

    private var localNowPlayingMovies = listOf<NowPlayingMovieViewData>()
}