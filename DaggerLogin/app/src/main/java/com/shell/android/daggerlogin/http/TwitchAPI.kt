package com.shell.android.daggerlogin.http

import com.shell.android.daggerlogin.http.twitch.TopGameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TwitchAPI {

    @GET("games/top")
    fun getTopGames(@Header("Client-Id") clientId: String): Call<TopGameResponse>
}