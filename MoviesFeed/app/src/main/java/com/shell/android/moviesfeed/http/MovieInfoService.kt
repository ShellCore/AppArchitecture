package com.shell.android.moviesfeed.http

import com.shell.android.moviesfeed.http.apimodel.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInfoService {

    @GET(".")
    fun getMovieInfo(@Query("t") title: String): Observable<MovieResponse>
}