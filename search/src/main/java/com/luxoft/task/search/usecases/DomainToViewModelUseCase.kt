package com.luxoft.task.search.usecases

import com.luxoft.task.search.models.domain.Results
import com.luxoft.task.search.models.view.SearchMovieViewData
import javax.inject.Inject

class DomainToViewModelUseCase @Inject constructor() {

    companion object {
        private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    }

    fun transform(results: List<Results>) =
        results.map {
            SearchMovieViewData(
                id = it.id,
                title = it.title,
                getPosterPath(it.posterPath)
            )
        }

    private fun getPosterPath(posterPath: String) = BASE_POSTER_PATH + posterPath
}