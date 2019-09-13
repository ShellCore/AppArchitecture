package com.shell.android.moviesfeed.root

import com.shell.android.moviesfeed.MainActivity
import com.shell.android.moviesfeed.http.MovieExtraInfoApiModule
import com.shell.android.moviesfeed.http.TopRatedApiModule
import com.shell.android.moviesfeed.movies.MoviesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, MoviesModule::class, TopRatedApiModule::class, MovieExtraInfoApiModule::class])
interface ApplicationComponent {

    fun inject(target: MainActivity)
}