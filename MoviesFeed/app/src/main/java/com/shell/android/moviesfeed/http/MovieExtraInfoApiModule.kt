package com.shell.android.moviesfeed.http

import com.shell.android.moviesfeed.BuildConfig
import dagger.Module

@Module
class MovieExtraInfoApiModule {

    companion object {
        const val BASE_URL = BuildConfig.OMDB_BASE_URL
    }
}