package com.shell.android.daggerlogin.login

interface LoginActivityMVP {

    interface Model {
        fun createUser(name: String, lastname: String)
        fun getUser(): User?
    }

    interface View {
        fun getName(): String
        fun getLastname(): String

        fun showUserNotAvailable()
        fun showIntpuError()
        fun showUserSaved()

        fun setName(name: String)
        fun setLastname(lastname: String)
    }

    interface Presenter {
        fun setView(view: LoginActivityMVP.View)

        fun loginBtnClicked()

        fun getCurrentUser()
    }
}