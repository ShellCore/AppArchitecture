package com.shell.android.daggerlogin.login

class MemoryRepository : LoginRepository {

    private var user: User? = null

    override fun saveUser(user: User?) {
        if (user == null) {
            this.user = getUser()
        } else {
            this.user = user
        }
    }

    override fun getUser(): User? {
        if (user == null) {
            user = User(name = "Antonio", lastname = "Banderas")
        }
        return user
    }
}