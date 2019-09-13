package com.shell.android.daggerlogin.http.twitch

data class Game(
    val _id: Int,
    val box: Box,
    val giantbomb_id: Int,
    val logo: Logo,
    val name: String,
    val popularity: Int
)