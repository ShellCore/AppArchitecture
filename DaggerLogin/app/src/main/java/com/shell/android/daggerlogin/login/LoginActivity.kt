package com.shell.android.daggerlogin.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shell.android.daggerlogin.BuildConfig
import com.shell.android.daggerlogin.R
import com.shell.android.daggerlogin.http.TwitchAPI
import com.shell.android.daggerlogin.http.twitch.Game
import com.shell.android.daggerlogin.http.twitch.Top
import com.shell.android.daggerlogin.http.twitch.TopGameResponse
import com.shell.android.daggerlogin.root.App
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
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

//        getTopGames()
        getTopGamesReactive()
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

    fun getTopGamesReactive() {
        twitchApi.getTopGamesObserbable(BuildConfig.TWITCH_CLIENT_ID)
            .flatMap(Function<TopGameResponse, Observable<Top>> {
                Observable.fromIterable(it.top)
            })
            .flatMap {
                Observable.just(it.game.name)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<String> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(gameName: String) {
                    Log.w("GAME NAME", gameName)
                }

                override fun onError(e: Throwable) {
                    Log.e("Observable", e.localizedMessage)
                }
            })
    }
}
