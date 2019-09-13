package com.shell.android.moviesfeed.movies

import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class ModelImpl(

    private val repository: MoviesRepository

) : MoviesMVP.Model {

    override fun result(): Observable<MoviesModel> {
        return Observable.zip(repository.getResultData(), repository.getCountryData(), object: BiFunction<Result, String, MoviesModel> {
            override fun apply(result: Result, country: String): MoviesModel {
                // TODO Cambiar result.toString() cuando tengamos el POJO de datos
                return MoviesModel(result.toString(), country, "")
            }

        })
    }
}