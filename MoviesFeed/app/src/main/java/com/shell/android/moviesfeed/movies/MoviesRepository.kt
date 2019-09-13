package com.shell.android.moviesfeed.movies

import io.reactivex.Observable

interface MoviesRepository {

    fun getResultData(): Observable<Result>
    fun getCountryData(): Observable<String>
}