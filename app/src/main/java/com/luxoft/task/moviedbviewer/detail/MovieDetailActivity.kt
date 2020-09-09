package com.luxoft.task.moviedbviewer.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.luxoft.task.moviedbviewer.R
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import com.luxoft.task.search.models.view.SearchMovieViewData
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class MovieDetailActivity : DaggerAppCompatActivity(), MovieDetailContract.View {

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter
    private lateinit var data: NowPlayingMovieViewData
    private lateinit var likeItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        data = intent.getParcelableExtra(movieId)!!
        presenter.attachView(this)
        Glide.with(this).load(data.posterUrl).into(movie_detail_poster)
        prepareView()
    }

    private fun prepareView() {
        overview.text = data.overview
        supportActionBar?.title = data.title
        release_date.text = "Release date: " + data.releaseDate
        vote_average.text = "Rating: " + data.voteAverage.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        likeItem = menu.findItem(R.id.action_like)
        presenter.isFavourite(data.id)
        return true
    }

    override fun setFavouriteMovie(status: Boolean) {
        if (status) {
            likeItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
            likeItem.setOnMenuItemClickListener {
                presenter.removeMovieFromFavourite(data.id)
                true
            }
        } else {
            likeItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_outline_favorite_border)
            likeItem.setOnMenuItemClickListener {
                presenter.addMovieToFavourite(data.id)
                true
            }
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    companion object {
        private const val movieId = "movie"
        fun startMovieDetailActivity(context: Context?, movie: NowPlayingMovieViewData) {
            context?.let {
                val intent =
                    Intent(it, MovieDetailActivity::class.java).apply { putExtra(movieId, movie) }
                it.startActivity(intent)
            }
        }
    }
}
