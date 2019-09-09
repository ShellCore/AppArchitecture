package com.shell.android.daggerlogin.login

interface LoginRepository {

    fun saveUser(user: User?)
    fun getUser(): User?
}