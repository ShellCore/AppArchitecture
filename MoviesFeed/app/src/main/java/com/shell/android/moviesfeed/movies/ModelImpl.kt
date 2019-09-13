package com.shell.android.moviesfeed.movies

import com.shell.android.moviesfeed.Movie
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class ModelImpl(

    private val repository: MoviesRepository

) : MoviesMVP.Model {

    override fun result(): Observable<Movie> {
        return Observable.zip(repository.getResultData(), repository.getCountryData(), object: BiFunction<Result, String, Movie> {
            override fun apply(result: Result, country: String): Movie {
                // TODO Cambiar result.toString() cuando tengamos el POJO de datos
                return Movie(result.toString(), country, "")
            }

        })
    }
}