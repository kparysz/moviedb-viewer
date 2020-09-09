package com.luxoft.task.moviedbviewer.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luxoft.task.moviedbviewer.R
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.search.models.view.SearchMovieViewData
import com.luxoft.task.nowplaying.presenter.NowPlayingContract
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_now_playing_movies.view.*
import javax.inject.Inject

class NowPlayingMoviesFragment : DaggerFragment(), NowPlayingContract.View {

    companion object {
        fun newInstance() = NowPlayingMoviesFragment()
    }

    @Inject
    lateinit var presenter: NowPlayingContract.Presenter

    private val adapter = NowPlayingMovieListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_now_playing_movies, container, false)
            .apply {
                this.now_playing_movies.adapter = adapter
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.getNowPlayingMovies()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun showNowPlayingMovies(movies: List<NowPlayingMovieViewData>) {
        adapter.addMovieList(movies)
    }

    override fun refresh() {
        adapter.notifyDataSetChanged()
    }
}
