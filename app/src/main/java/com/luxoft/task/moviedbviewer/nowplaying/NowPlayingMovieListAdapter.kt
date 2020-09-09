package com.luxoft.task.moviedbviewer.nowplaying

import android.view.View
import com.luxoft.task.moviedbviewer.R
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.search.models.view.SearchMovieViewData
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow

class NowPlayingMovieListAdapter : BaseAdapter() {

    init {
        addSection(ArrayList<NowPlayingMovieViewData>())
    }

    fun addMovieList(movies: List<NowPlayingMovieViewData>) {
        movies.isNullOrEmpty().not().apply {
            sections()[0].addAll(movies)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.now_playing_movie_view

    override fun viewHolder(layout: Int, view: View) = NowPlayingMovieListViewHolder(view)
}
