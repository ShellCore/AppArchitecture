package com.shell.android.daggerlogin.http

import com.shell.android.daggerlogin.http.twitch.TopGameResponse
import retrofit2.Call
import retrofit2.http.GET

interface TwitchAPI {

    @GET("games/top")
    fun getTopGames(): Call<TopGameResponse>
}