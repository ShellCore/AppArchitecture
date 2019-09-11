package com.shell.android.moviesfeed.root

import com.shell.android.moviesfeed.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(target: MainActivity)
}