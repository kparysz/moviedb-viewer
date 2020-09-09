package com.luxoft.task.nowplaying.models.domain

import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(
    @SerializedName("results")
    val results: List<Results>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("total_pages")
    val totalPages: Int
)