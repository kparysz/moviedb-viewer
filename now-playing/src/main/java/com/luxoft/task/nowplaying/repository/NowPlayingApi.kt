package com.luxoft.task.nowplaying.repository

import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import io.reactivex.Single

interface NowPlayingApi {

    fun getNowPlaying(): Single<List<NowPlayingMovieViewData>>
}