package com.luxoft.task.nowplaying.presenter

import com.luxoft.task.base.BaseMvpPresenter
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData

interface NowPlayingContract {

    interface View {
        fun showNowPlayingMovies(movies: List<NowPlayingMovieViewData>)
        fun refresh()
        fun showError()
    }

    abstract class Presenter : BaseMvpPresenter<View>() {
        abstract fun getNowPlayingMovies()
        abstract fun addMovieToFavourite(movieId: Int)
        abstract fun removeMovieFromFavourite(movieId: Int)
    }
}