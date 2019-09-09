package com.shell.android.daggerlogin.root

import android.app.Application
import com.shell.android.daggerlogin.login.LoginModule

class App: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .loginModule(LoginModule())
            .build()
    }
}