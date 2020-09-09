package com.luxoft.task.nowplaying.repository

import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.nowplaying.network.MoviesApi
import com.luxoft.task.nowplaying.usecases.DomainToViewModelUseCase
import io.reactivex.Single
import javax.inject.Inject

class NowPlayingRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val domainToViewModelUseCase: DomainToViewModelUseCase
) : NowPlayingApi {

    override fun getNowPlaying(): Single<List<NowPlayingMovieViewData>> =
        moviesApi.getNowPlayingMovies("34504426320940f320b83c20b38dcb5e", "en-US", 1)
            .map { it -> domainToViewModelUseCase.transform(it.results) }
}