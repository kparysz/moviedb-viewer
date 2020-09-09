package com.luxoft.task.search.models.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchMovieViewData(
    val id: Int,
    val title: String,
    val posterUrl: String?,
) : Parcelable