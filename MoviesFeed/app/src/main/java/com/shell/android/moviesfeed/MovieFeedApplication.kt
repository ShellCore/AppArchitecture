package com.shell.android.moviesfeed

import android.app.Application
import com.shell.android.moviesfeed.root.ApplicationComponent
import com.shell.android.moviesfeed.root.ApplicationModule
import com.shell.android.moviesfeed.root.DaggerApplicationComponent

class MovieFeedApplication: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}