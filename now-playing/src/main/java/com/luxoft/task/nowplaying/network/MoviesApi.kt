package com.luxoft.task.nowplaying.network

import com.luxoft.task.base.models.domain.NowPlayingResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<NowPlayingResponse>
}