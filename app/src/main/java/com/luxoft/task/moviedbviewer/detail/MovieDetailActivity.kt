package com.luxoft.task.moviedbviewer.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.luxoft.task.favourtiesdb.repositories.FavouritesMovieRepository
import com.luxoft.task.moviedbviewer.R
import com.luxoft.task.nowplaying.models.view.NowPlayingMovieViewData
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class MovieDetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var repository: FavouritesMovieRepository
    private lateinit var data: NowPlayingMovieViewData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        data = intent.getParcelableExtra(movieId)!!

        Glide.with(this).load(data.posterUrl).into(movie_detail_poster)
        overview.text = data.overview
        supportActionBar?.title = data.title
        release_date.text = "Release date: " + data.releaseDate
        vote_average.text = "Rating: " + data.voteAverage.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        val likeItem: MenuItem = menu.findItem(R.id.action_like)

        likeItem.setOnMenuItemClickListener {
            repository.addToFavourites(data.id)
            true
        }
        return true
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
