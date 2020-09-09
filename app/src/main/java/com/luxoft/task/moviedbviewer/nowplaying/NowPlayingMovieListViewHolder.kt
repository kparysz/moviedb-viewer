package com.luxoft.task.moviedbviewer.nowplaying

import android.view.View
import com.bumptech.glide.Glide
import com.luxoft.task.moviedbviewer.R
import com.luxoft.task.moviedbviewer.detail.MovieDetailActivity
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.search.models.view.SearchMovieViewData
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.now_playing_movie_view.view.*

class NowPlayingMovieListViewHolder constructor(
    view: View
) : BaseViewHolder(view) {

    private lateinit var movie: NowPlayingMovieViewData

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is NowPlayingMovieViewData) {
            movie = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            loadImage()
            setFavouriteIcon()
            favourites.setOnClickListener {
                movie.favouriteAction.invoke(movie.id)
            }
        }
    }

    private fun View.loadImage() {
        movie.posterUrl?.let {
            Glide.with(context)
                .load(movie.posterUrl)
                .into(movie_poster)
        }
    }

    private fun View.setFavouriteIcon() {
        if (movie.isLiked) {
            favourites.setImageResource(R.drawable.ic_favorite)
        } else {
            favourites.setImageResource(R.drawable.ic_outline_favorite_border)
        }
    }

    override fun onClick(view: View) {
        MovieDetailActivity.startMovieDetailActivity(context(), movie)
    }

    override fun onLongClick(view: View?) = false
}
