package com.luxoft.task.search.repository

import com.luxoft.task.search.models.view.SearchMovieViewData
import io.reactivex.Single

interface SearchMovieApi {

    fun searchMovie(query: String): Single<List<SearchMovieViewData>>
}