package com.shell.android.daggerlogin.root

import com.shell.android.daggerlogin.login.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(target: MainActivity)
}