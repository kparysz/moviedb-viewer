package com.luxoft.task.search.presenter

import com.luxoft.task.base.rx.ApplicationScheduler
import com.luxoft.task.search.repository.SearchMovieApi
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val scheduler: ApplicationScheduler,
    private val searchMovieApi: SearchMovieApi
) : SearchContract.Presenter() {

    override fun findMovie(query: String) {
        scheduler.schedule(
            searchMovieApi.searchMovie(query),
            { view.showSearchResults(it) },
            { view.showError() },
            this
        )
    }
}