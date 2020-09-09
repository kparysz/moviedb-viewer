package com.luxoft.task.search.repository

import com.luxoft.task.search.models.view.SearchMovieViewData
import com.luxoft.task.search.network.SearchApi
import com.luxoft.task.search.usecases.DomainToViewModelUseCase
import io.reactivex.Single
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApi: SearchApi,
    private val useCase: DomainToViewModelUseCase
) : SearchMovieApi {

    override fun searchMovie(query: String): Single<List<SearchMovieViewData>> =
        searchApi.searchMovie(
            "34504426320940f320b83c20b38dcb5e",
            "en-US",
            query,
            false,
            1
        ).map { useCase.transform(it.results) }
}