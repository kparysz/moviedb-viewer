package com.luxoft.task.nowplaying.presenter

import com.luxoft.task.base.BaseMvpPresenter
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.search.models.view.SearchMovieViewData

interface NowPlayingContract {

    interface View {
        fun showNowPlayingMovies(movies: List<NowPlayingMovieViewData>)
        fun fillAutoCompleteAdapter(movies: List<NowPlayingMovieViewData>)
        fun refresh(nowPlayingMovies: List<NowPlayingMovieViewData>)
        fun showError()
    }

    abstract class Presenter : BaseMvpPresenter<View>() {
        abstract fun getNowPlayingMovies()
        abstract fun findMovie(query: String)
    }
}