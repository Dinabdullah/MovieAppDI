package com.example.movieapp.di

import com.example.movieapp.domain.usecase.moviedetails.GetMovieDetailsUseCase
import com.example.movieapp.domain.usecase.moviedetails.GetMovieDetailsUseCaseImpl
import com.example.movieapp.domain.usecase.popularmovies.GetPopularMoviesUseCase
import com.example.movieapp.domain.usecase.popularmovies.GetPopularMoviesUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<GetPopularMoviesUseCase> { GetPopularMoviesUseCaseImpl(get()) }
    single<GetMovieDetailsUseCase> { GetMovieDetailsUseCaseImpl(get()) }
}