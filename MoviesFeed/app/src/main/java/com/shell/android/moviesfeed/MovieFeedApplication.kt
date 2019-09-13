package com.shell.android.moviesfeed

import android.app.Application
import com.shell.android.moviesfeed.http.MovieExtraInfoApiModule
import com.shell.android.moviesfeed.http.TopRatedApiModule
import com.shell.android.moviesfeed.movies.MoviesModule
import com.shell.android.moviesfeed.root.ApplicationComponent
import com.shell.android.moviesfeed.root.ApplicationModule
import com.shell.android.moviesfeed.root.DaggerApplicationComponent

class MovieFeedApplication: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .moviesModule(MoviesModule())
            .topRatedApiModule(TopRatedApiModule())
            .movieExtraInfoApiModule(MovieExtraInfoApiModule())
            .build()
    }
}