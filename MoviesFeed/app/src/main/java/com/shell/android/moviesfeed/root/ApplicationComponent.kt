package com.shell.android.moviesfeed.root

import com.shell.android.moviesfeed.MainActivity
import com.shell.android.moviesfeed.http.TopRatedApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, TopRatedApiModule::class])
interface ApplicationComponent {

    fun inject(target: MainActivity)
}