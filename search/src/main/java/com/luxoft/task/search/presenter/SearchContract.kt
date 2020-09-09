package com.luxoft.task.search.presenter

import com.luxoft.task.base.BaseMvpPresenter
import com.luxoft.task.search.models.view.SearchMovieViewData

interface SearchContract {

    interface View {
        fun showSearchResults(movies: List<SearchMovieViewData>)
        fun showError()
    }

    abstract class Presenter : BaseMvpPresenter<View>() {
        abstract fun findMovie(query: String)
    }
}