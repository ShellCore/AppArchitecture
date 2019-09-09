package com.shell.android.daggerlogin.login

import androidx.annotation.Nullable

class LoginActivityPresenter(val model: LoginActivityMVP.Model) : LoginActivityMVP.Presenter {

    @Nullable
    private var view: LoginActivityMVP.View? = null

    override fun setView(view: LoginActivityMVP.View) {
        this.view = view
    }

    override fun loginBtnClicked() {
        if (view != null) {
            if (view!!.getName().trim().isEmpty() || view!!.getLastname().trim().isEmpty()) {
                view!!.showIntpuError()
            } else {
                model.createUser(view!!.getName().trim(), view!!.getLastname().trim())
                view!!.showUserSaved()
            }
        }
    }

    override fun getCurrentUser() {
        val user = model.getUser()
        if (user == null) {
            if (view != null) {
                view!!.showUserNotAvailable()
            }
        } else {
            if (view != null) {
                view!!.setName(user.name)
                view!!.setLastname(user.lastname)
            }
        }
    }
}