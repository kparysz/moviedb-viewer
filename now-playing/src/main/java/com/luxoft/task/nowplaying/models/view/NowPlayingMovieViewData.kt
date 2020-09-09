package com.luxoft.task.nowplaying.models.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NowPlayingMovieViewData(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val posterUrl: String?,
    val isLiked: Boolean
) : Parcelable {
    var favouriteAction: (Int) -> Unit = {}
}