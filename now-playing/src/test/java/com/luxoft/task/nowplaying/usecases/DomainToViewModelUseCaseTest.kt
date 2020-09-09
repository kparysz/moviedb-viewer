package com.luxoft.task.nowplaying.usecases

import com.luxoft.task.base.models.domain.Results
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieApi
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.Test

class DomainToViewModelUseCaseTest {

    private val mockFavouritesMovieApi: FavouritesMovieApi = mock()

    val systemUnderTest = DomainToViewModelUseCase(
        mockFavouritesMovieApi
    )

    @Test
    fun `transform object from domain to view model`() {
        val transform = systemUnderTest.transform(getResults())

        assertThat(transform).hasSize(1)
        assertThat(transform[0]).isInstanceOf(NowPlayingMovieViewData::class.java)
    }

    private fun getResults() = listOf(
        Results(
            0.0,
            1,
            false,
            "posterPath",
            123,
            false,
            "backdropPath",
            "en",
            "originalTitle",
            listOf(1, 2, 3, 4),
            "title",
            1.0,
            "overview",
            "releaseDate"
        )
    )
}