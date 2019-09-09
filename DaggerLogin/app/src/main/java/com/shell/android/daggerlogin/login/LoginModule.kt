package com.shell.android.daggerlogin.login

import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun provideLoginActivityPresenter(model: LoginActivityMVP.Model): LoginActivityMVP.Presenter =
        LoginActivityPresenter(model)

    @Provides
    fun provideLoginActivityModel(repository: LoginRepository): LoginActivityMVP.Model =
        LoginActivityModel(repository)

    @Provides
    fun provideLoginRepository(): LoginRepository = MemoryRepository()
}