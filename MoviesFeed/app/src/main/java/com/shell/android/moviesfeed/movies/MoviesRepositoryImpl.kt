package com.shell.android.moviesfeed.movies

import com.shell.android.moviesfeed.http.MovieInfoService
import com.shell.android.moviesfeed.http.MoviesApiServices
import com.shell.android.moviesfeed.http.apimodel.MovieResponse
import com.shell.android.moviesfeed.http.apimodel.Result
import com.shell.android.moviesfeed.http.apimodel.TopRatedResponse
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

class MoviesRepositoryImpl(
    private val moviesService: MoviesApiServices,
    private val infoService: MovieInfoService
) : MoviesRepository {

    companion object {
        const val CACHE_LIFETIME = 20 * 1000
    }

    private var countries: ArrayList<String> = ArrayList()
    private var results: ArrayList<Result> = ArrayList()
    private var lastTimeStamp: Long = System.currentTimeMillis()

    override fun getResultData(): Observable<Result> {
        return getResultFromCache().switchIfEmpty(getResultFromNetwork())
    }

    override fun getResultFromNetwork(): Observable<Result> {
        val topRatedObservable: Observable<TopRatedResponse> = moviesService.getTopRatedMovies(1)
            .concatWith(moviesService.getTopRatedMovies(2))
            .concatWith(moviesService.getTopRatedMovies(3))

        return topRatedObservable.concatMap(object :
            Function<TopRatedResponse, Observable<Result>> {
            override fun apply(response: TopRatedResponse): Observable<Result> {
                return Observable.fromIterable(response.results)
            }

        }).doOnNext(object : Consumer<Result> {
            override fun accept(result: Result) {
                results.add(result)
            }
        })
    }

    override fun getResultFromCache(): Observable<Result> {
        return if (isUpdated()) {
            Observable.fromIterable(results)
        } else {
            lastTimeStamp = System.currentTimeMillis()
            results.clear()
            Observable.empty()
        }
    }

    override fun getCountryData(): Observable<String> {
        return getCountryFromCache().switchIfEmpty(getCountryFromNetwork())
    }

    override fun getCountryFromNetwork(): Observable<String> {
        return getResultFromNetwork().concatMap(object :
            Function<Result, Observable<MovieResponse>> {
            override fun apply(result: Result): Observable<MovieResponse> {
                return infoService.getMovieInfo(result.title)
            }
        }).concatMap(object : Function<MovieResponse, Observable<String>> {
            override fun apply(t: MovieResponse): Observable<String> {
                return if(t.country.isNullOrEmpty()) {
                    Observable.just("Desconocido")
                } else {
                    Observable.just(t.country)
                }
            }

        }).doOnNext(object : Consumer<String> {
            override fun accept(country: String) {
                countries.add(country)
            }
        })
    }

    override fun getCountryFromCache(): Observable<String> {
        return if (isUpdated()) {
            Observable.fromIterable(countries)
        } else {
            lastTimeStamp = System.currentTimeMillis()
            countries.clear()
            Observable.empty()
        }
    }

    private fun isUpdated(): Boolean {
        return (System.currentTimeMillis() - lastTimeStamp) < CACHE_LIFETIME
    }
}