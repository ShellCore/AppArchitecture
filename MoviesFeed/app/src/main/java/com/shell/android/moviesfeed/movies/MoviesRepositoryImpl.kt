package com.shell.android.moviesfeed.movies

import com.shell.android.moviesfeed.http.MovieInfoService
import com.shell.android.moviesfeed.http.MoviesApiServices
import io.reactivex.Observable

class MoviesRepositoryImpl(
    private val moviesService: MoviesApiServices,
    private val infoService: MovieInfoService
) : MoviesRepository {

    override fun getResultData(): Observable<Result> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCountryData(): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}