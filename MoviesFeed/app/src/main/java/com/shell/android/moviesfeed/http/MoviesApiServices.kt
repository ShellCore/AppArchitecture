package com.shell.android.moviesfeed.http

import com.shell.android.moviesfeed.http.apimodel.TopRatedResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiServices {

    @GET("top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Observable<TopRatedResponse>
}