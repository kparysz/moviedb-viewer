package com.luxoft.task.nowplaying.repository

import com.luxoft.task.base.models.domain.Dates
import com.luxoft.task.base.models.domain.NowPlayingResponse
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.nowplaying.network.MoviesApi
import com.luxoft.task.nowplaying.usecases.DomainToViewModelUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

class NowPlayingRepositoryTest {

    private val mockMoviesApi: MoviesApi = mock {
        on {
            getNowPlayingMovies(
                any(),
                any(),
                any()
            )
        } doReturn Single.just(getNowPlayingResponse())
    }
    private val mockDomainToViewModelUseCase: DomainToViewModelUseCase = mock {
        on { transform(any()) } doReturn getNowPlayingMovieViewData()
    }

    val systemUnderTest = NowPlayingRepository(
        mockMoviesApi,
        mockDomainToViewModelUseCase
    )

    @Test
    fun `get now playing movies`() {
        systemUnderTest.getNowPlaying().test()
            .assertValue { it.size == 1 }
            .assertValue { it[0].id == 1 }
            .assertValue { it[0].title == "title" }
    }

    private fun getNowPlayingMovieViewData() = listOf(
        NowPlayingMovieViewData(
            1,
            "title",
            "overview",
            "releaseDate",
            1.0,
            "",
            false
        )
    )

    private fun getNowPlayingResponse() = NowPlayingResponse(
        emptyList(),
        1,
        1,
        Dates("0", "1"),
        1
    )
}