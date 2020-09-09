package com.luxoft.task.nowplaying.network

import com.luxoft.task.nowplaying.models.domain.NowPlayingResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    //https://api.themoviedb.org/3/movie/now_playing?api_key=34504426320940f320b83c20b38dcb5e&language=en-US&page=1
    //?api_key=34504426320940f320b83c20b38dcb5e&language=en-US&page=1
    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<NowPlayingResponse>
}