package com.example.movieapp.di

import com.example.movieapp.common.Constants
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.remote.MovieService
import com.example.movieapp.data.remote.MovieServiceImpl
import com.example.movieapp.data.repository.moviedetails.MovieDetailsImpl
import com.example.movieapp.data.repository.popularmovies.PopularMoviesImpl
import com.example.movieapp.domain.repository.moviedetails.MovieDetails
import com.example.movieapp.domain.repository.popularmovies.PopularMovies
import com.example.movieapp.domain.usecase.moviedetails.GetMovieDetailsUseCase
import com.example.movieapp.domain.usecase.moviedetails.GetMovieDetailsUseCaseImpl
import com.example.movieapp.domain.usecase.popularmovies.GetPopularMoviesUseCase
import com.example.movieapp.domain.usecase.popularmovies.GetPopularMoviesUseCaseImpl
import com.example.movieapp.ui.screens.details.DetailsViewModel
import com.example.movieapp.ui.screens.home.HomeScreenViewModel
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

val repositoryModule = module {
    single<MovieService> { MovieServiceImpl(get()) }

    single<PopularMovies> { PopularMoviesImpl(get()) }
    single<MovieDetails> { MovieDetailsImpl(get()) }
}

val useCaseModule = module {
    single<GetPopularMoviesUseCase> { GetPopularMoviesUseCaseImpl(get()) }
    single<GetMovieDetailsUseCase> { GetMovieDetailsUseCaseImpl(get()) }

}

val viewModelModule = module {
    viewModel<HomeScreenViewModel> { HomeScreenViewModel(get()) }
    viewModel<DetailsViewModel> { DetailsViewModel(get()) }
}

val appModule = listOf(
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule,
)

