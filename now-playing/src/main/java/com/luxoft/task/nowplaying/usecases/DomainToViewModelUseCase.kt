package com.luxoft.task.nowplaying.usecases

import com.luxoft.task.base.models.domain.Results
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieApi
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import javax.inject.Inject

class DomainToViewModelUseCase @Inject constructor(
    private val favouritesMovieApi: FavouritesMovieApi
) {

    companion object {
        private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    }

    fun transform(results: List<Results>) =
        results.map {
            NowPlayingMovieViewData(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                getPosterPath(it.posterPath),
                isLiked = false
            )
        }

    private fun getPosterPath(posterPath: String) = BASE_POSTER_PATH + posterPath
}