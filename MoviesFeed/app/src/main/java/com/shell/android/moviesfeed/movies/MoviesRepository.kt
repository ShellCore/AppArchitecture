package com.shell.android.moviesfeed.movies

import com.shell.android.moviesfeed.http.apimodel.Result
import io.reactivex.Observable

interface MoviesRepository {

    fun getResultData(): Observable<Result>
    fun getResultFromNetwork(): Observable<Result>
    fun getResultFromCache(): Observable<Result>

    fun getCountryData(): Observable<String>
    fun getCountryFromNetwork(): Observable<String>
    fun getCountryFromCache(): Observable<String>
}