package com.shell.android.daggerlogin.login

class LoginActivityModel(val repository: LoginRepository) : LoginActivityMVP.Model {

    override fun createUser(name: String, lastname: String) {
        // Lógica de business
        repository.saveUser(User(name = name, lastname = lastname))
    }

    override fun getUser(): User? {
        // Lógica de business
        return repository.getUser()
    }
}