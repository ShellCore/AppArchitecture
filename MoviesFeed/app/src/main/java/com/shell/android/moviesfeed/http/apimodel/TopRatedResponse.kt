package com.shell.android.moviesfeed.http.apimodel


import com.google.gson.annotations.SerializedName

data class TopRatedResponse(
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)