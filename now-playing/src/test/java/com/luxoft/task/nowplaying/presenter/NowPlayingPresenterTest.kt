package com.luxoft.task.nowplaying.presenter

import com.luxoft.task.base.rx.ApplicationScheduler
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieApi
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.nowplaying.repository.NowPlayingApi
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class NowPlayingPresenterTest {

    private val mockScheduler: ApplicationScheduler = ApplicationTestScheduler()
    private val mockNowPlayingApi: NowPlayingApi = mock {
        on { getNowPlaying() } doReturn Single.just(emptyList())
    }
    private val mockView: NowPlayingContract.View = mock()
    private val mockFavouritesMovieApi: FavouritesMovieApi = mock()

    val systemUnderTest = NowPlayingPresenter(
        mockScheduler,
        mockNowPlayingApi,
        mockFavouritesMovieApi
    )

    @Before
    fun setUp() {
        systemUnderTest.attachView(mockView)
    }

    @Test
    fun `show now playing movies`() {
        systemUnderTest.getNowPlayingMovies()

        verify(mockView).showNowPlayingMovies(any())
    }

    @Test
    fun `should show one object on now playing movie list`() {
        whenever(mockNowPlayingApi.getNowPlaying()).doReturn(playingNowMovies())
        systemUnderTest.getNowPlayingMovies()

        argumentCaptor<List<NowPlayingMovieViewData>>().apply {
            verify(mockView).showNowPlayingMovies(capture())

            assertThat(firstValue).hasSize(1)
        }
    }

    private fun playingNowMovies() = Single.just(
        listOf(
            NowPlayingMovieViewData(0, "title", "overview", "relaseDate", 1.0, "url", false)
        )
    )
}