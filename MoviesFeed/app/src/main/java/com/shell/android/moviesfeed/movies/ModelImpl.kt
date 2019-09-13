package com.shell.android.moviesfeed.movies

import com.shell.android.moviesfeed.BuildConfig
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import com.shell.android.moviesfeed.http.apimodel.Result

class ModelImpl(

    private val repository: MoviesRepository

) : MoviesMVP.Model {

    companion object {
        const val IMAGE_BASE_URL = BuildConfig.MOVIEDB_IMAGE_BASE_URL
    }

    override fun result(): Observable<MoviesModel> {
        return Observable.zip(repository.getResultData(), repository.getCountryData(),
            BiFunction<Result, String, MoviesModel> { result, country ->
                MoviesModel(
                    result.title,
                    country,
                    "$IMAGE_BASE_URL${result.posterPath}"
                )
            })
    }
}