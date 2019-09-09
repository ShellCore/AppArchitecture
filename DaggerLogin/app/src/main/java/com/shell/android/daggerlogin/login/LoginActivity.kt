package com.shell.android.daggerlogin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shell.android.daggerlogin.R
import com.shell.android.daggerlogin.root.App
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginActivityMVP.View {

    @Inject
    lateinit var presenter: LoginActivityMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupInjection()

        btnLogin.setOnClickListener {
            presenter.loginBtnClicked()
        }
    }

    private fun setupInjection() {
        (application as App).component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.getCurrentUser()
    }

    override fun getName(): String {
        return tilName.editText!!.text.toString()
    }

    override fun getLastname(): String {
        return tilLastname.editText!!.text.toString()
    }

    override fun showUserNotAvailable() {
        Toast.makeText(this, "Error, el usuario no está disponible", Toast.LENGTH_SHORT).show()
    }

    override fun showIntpuError() {
        Toast.makeText(this, "Error, el nombre ni el apellido pueden estar vacios", Toast.LENGTH_SHORT).show()
    }

    override fun showUserSaved() {
        Toast.makeText(this, "Usuario almacenado correctamente", Toast.LENGTH_SHORT).show()
    }

    override fun setName(name: String) {
        tilName.editText!!.setText(name)
    }

    override fun setLastname(lastname: String) {
        tilLastname.editText!!.setText(lastname)
    }
}
