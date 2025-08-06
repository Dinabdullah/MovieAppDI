package com.example.movieapp.di

import com.example.movieapp.common.Constants
import com.example.movieapp.data.remote.MovieApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<MovieApi> { get<Retrofit>().create(MovieApi::class.java) }
    single {
        val apiKeyInterceptor = okhttp3.Interceptor { chain ->
            val originalRequest = chain.request()
            val newUrl = originalRequest.url.newBuilder()
                .addQueryParameter(Constants.API_KEY, Constants.API_KEY_VALUE)
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }

        okhttp3.OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }
}