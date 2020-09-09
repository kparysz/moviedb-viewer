package com.luxoft.task.nowplaying.presenter

import com.luxoft.task.base.rx.ApplicationScheduler
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieApi
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.nowplaying.repository.NowPlayingApi
import com.luxoft.task.search.repository.SearchMovieApi
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Completable
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
    private val mockSearchMovieApi: SearchMovieApi = mock()
    private val mockFavouritesMovieApi: FavouritesMovieApi = mock {
        on { addToFavourites(any()) } doReturn Completable.complete()
        on { removeFromFavourites(any()) } doReturn Completable.complete()
    }

    val systemUnderTest = NowPlayingPresenter(
        mockScheduler,
        mockNowPlayingApi,
        mockSearchMovieApi,
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
        verify(mockView).fillAutoCompleteAdapter(any())
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

    @Test
    fun `remove movie from db when user click favourite icon`() {
        whenever(mockNowPlayingApi.getNowPlaying()).doReturn(playingNowMovies(false))
        systemUnderTest.getNowPlayingMovies()

        argumentCaptor<List<NowPlayingMovieViewData>>().apply {
            verify(mockView).showNowPlayingMovies(capture())
            firstValue.first().favouriteAction.invoke(0)

            verify(mockFavouritesMovieApi).addToFavourites(0)
            verify(mockView).refresh()
        }
    }

    @Test
    fun `add movie from db when user click favourite icon`() {
        whenever(mockNowPlayingApi.getNowPlaying()).doReturn(playingNowMovies(true))
        systemUnderTest.getNowPlayingMovies()

        argumentCaptor<List<NowPlayingMovieViewData>>().apply {
            verify(mockView).showNowPlayingMovies(capture())

            firstValue.first().favouriteAction.invoke(0)

            verify(mockFavouritesMovieApi).removeFromFavourites(0)
            verify(mockView).refresh()
        }
    }

    @Test
    fun `show error when exception throws`() {
        whenever(mockNowPlayingApi.getNowPlaying()).doReturn(Single.error(Throwable()))
        systemUnderTest.getNowPlayingMovies()

        verify(mockView).showError()
    }

    private fun playingNowMovies(isLiked: Boolean = false) = Single.just(
        listOf(
            NowPlayingMovieViewData(0, "title", "overview", "relaseDate", 1.0, "url", isLiked)
        )
    )
}