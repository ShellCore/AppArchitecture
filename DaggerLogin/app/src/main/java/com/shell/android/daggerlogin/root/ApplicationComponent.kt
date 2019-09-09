package com.shell.android.daggerlogin.root

import com.shell.android.daggerlogin.login.LoginActivity
import com.shell.android.daggerlogin.login.LoginModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, LoginModule::class])
interface ApplicationComponent {

    fun inject(target: LoginActivity)
}