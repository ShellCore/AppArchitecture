package com.shell.android.moviesfeed.http

import com.shell.android.moviesfeed.BuildConfig
import dagger.Module

@Module
class TopRatedApiModule {

    companion object {
        const val BASE_URL = BuildConfig.MOVIEDB_BASE_URL
        const val API_KEY = BuildConfig.MOVIEDB_API_KEY
    }
}