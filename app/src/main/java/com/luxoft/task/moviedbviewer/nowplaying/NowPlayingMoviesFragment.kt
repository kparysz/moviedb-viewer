package com.luxoft.task.moviedbviewer.nowplaying

import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.*
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import com.luxoft.task.moviedbviewer.R
import com.luxoft.task.moviedbviewer.detail.MovieDetailActivity
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.nowplaying.presenter.NowPlayingContract
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_now_playing_movies.*
import kotlinx.android.synthetic.main.fragment_now_playing_movies.view.*
import javax.inject.Inject

class NowPlayingMoviesFragment : DaggerFragment(), NowPlayingContract.View {

    companion object {
        fun newInstance() = NowPlayingMoviesFragment()
    }

    @Inject
    lateinit var presenter: NowPlayingContract.Presenter

    private val adapter = NowPlayingMovieListAdapter()
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_now_playing_movies, container, false)
            .apply {
                this.now_playing_movies.adapter = adapter
                this.swipe_to_refresh.setOnRefreshListener {
                    this.swipe_to_refresh.isRefreshing = true
                    presenter.getNowPlayingMovies()
                }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_prompt)
        searchView.findViewById<AutoCompleteTextView>(R.id.search_src_text).threshold = 1

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun showNowPlayingMovies(movies: List<NowPlayingMovieViewData>) {
        adapter.addMovieList(movies)
        swipe_to_refresh.isRefreshing = false
    }

    override fun fillAutoCompleteAdapter(movies: List<NowPlayingMovieViewData>) {
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.title)
        val cursorAdapter = SimpleCursorAdapter(
            context,
            R.layout.search_view,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
        searchView.suggestionsAdapter = cursorAdapter
        setTextListener(cursorAdapter, movies)
        setSuggestionListener(movies)
    }

    private fun setSuggestionListener(movies: List<NowPlayingMovieViewData>) {
        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                val movieTitle =
                    cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                searchView.setQuery(movieTitle, false)

                MovieDetailActivity.startMovieDetailActivity(
                    context,
                    movies.first { it.title == movieTitle })
                return true
            }
        })
    }

    private fun setTextListener(
        cursorAdapter: SimpleCursorAdapter,
        movies: List<NowPlayingMovieViewData>
    ) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.findMovie(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                query?.let {
                    movies.forEachIndexed { index, suggestion ->
                        if (suggestion.title.contains(query, true))
                            cursor.addRow(arrayOf(index, suggestion.title))
                    }
                }
                cursorAdapter.changeCursor(cursor)
                return true
            }
        })
    }

    override fun refresh() {
    }

    override fun showError() {
        swipe_to_refresh.isRefreshing = false
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
    }
}
