package com.luxoft.task.search.network

import com.luxoft.task.base.models.domain.SearchResultResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/3/search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("page") page: Int
    ): Single<SearchResultResponse>
}