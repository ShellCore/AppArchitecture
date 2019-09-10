package com.shell.android.daggerlogin.http

import com.shell.android.daggerlogin.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class TwitchModule {

    companion object {
        const val BASE_URL = BuildConfig.TWITCH_BASE_URL
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideTwitchService(): TwitchAPI {
        return provideRetrofit(BASE_URL, provideHttpClient())
            .create(TwitchAPI::class.java)
    }
}