package com.shell.android.daggerlogin.http.twitch

data class TopGameResponse(
    val _total: Int,
    val top: List<Top>
)