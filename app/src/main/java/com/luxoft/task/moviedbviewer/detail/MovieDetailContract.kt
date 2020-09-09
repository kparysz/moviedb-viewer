package com.luxoft.task.moviedbviewer.detail

import com.luxoft.task.base.BaseMvpPresenter

interface MovieDetailContract {

    interface View {
        fun setFavouriteMovie(status: Boolean)
    }

    abstract class Presenter : BaseMvpPresenter<View>() {
        abstract fun isFavourite(movieId: Int)
        abstract fun addMovieToFavourite(movieId: Int)
        abstract fun removeMovieFromFavourite(movieId: Int)
    }
}