package com.example.movieapp.di

import com.example.movieapp.common.Constants
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.example.movieapp.domain.usecase.GetPopularMoviesUseCaseImpl
import com.example.movieapp.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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
                .addQueryParameter("api_key", Constants.API_KEY)
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

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}

val useCaseModule = module {
    single<GetPopularMoviesUseCase> { GetPopularMoviesUseCaseImpl(get()) }
}

val viewModelModule = module {
    viewModel<HomeScreenViewModel> { HomeScreenViewModel(get()) }
}

val appModule = listOf(
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)

