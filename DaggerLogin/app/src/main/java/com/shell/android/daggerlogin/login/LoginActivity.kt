package com.shell.android.daggerlogin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.shell.android.daggerlogin.BuildConfig
import com.shell.android.daggerlogin.R
import com.shell.android.daggerlogin.http.TwitchAPI
import com.shell.android.daggerlogin.http.twitch.TopGameResponse
import com.shell.android.daggerlogin.root.App
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginActivityMVP.View {

    @Inject
    lateinit var presenter: LoginActivityMVP.Presenter

    @Inject
    lateinit var twitchApi: TwitchAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupInjection()

        btnLogin.setOnClickListener {
            presenter.loginBtnClicked()
        }

        getTopGames()
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

    fun getTopGames() {
        // Ejemplo de uso de la api de Twitch con retrofit
        val call = twitchApi.getTopGames(BuildConfig.TWITCH_CLIENT_ID)
        call.enqueue(object: Callback<TopGameResponse> {
            override fun onResponse(
                call: Call<TopGameResponse>,
                response: Response<TopGameResponse>
            ) {
                if (response.isSuccessful) {
                    val resp = response.body()
                    resp?.top?.forEach {
                        Log.w("Games", it.game.name)
                    }

                }
            }

            override fun onFailure(call: Call<TopGameResponse>, t: Throwable) {
                Log.e("LoginActivity", t.localizedMessage)
            }

        })
    }
}
