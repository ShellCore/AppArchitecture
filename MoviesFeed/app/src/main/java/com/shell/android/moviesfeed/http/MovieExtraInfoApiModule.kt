package com.shell.android.moviesfeed.http

import com.shell.android.moviesfeed.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MovieExtraInfoApiModule {

    companion object {
        const val BASE_URL = BuildConfig.OMDB_BASE_URL
        const val API_KEY = BuildConfig.OMDB_API_KEY
    }

    @Provides
    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(object: Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    val url = request.url
                        .newBuilder()
                        .addQueryParameter("apikey", API_KEY)
                        .build()
                    request = request.newBuilder()
                        .url(url)
                        .build()
                    return chain.proceed(request)
                }
            })
            .build()
    }

    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(): MovieInfoService {
        return provideRetrofit(BASE_URL, provideClient())
            .create(MovieInfoService::class.java)
    }
}