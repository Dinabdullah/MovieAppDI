package com.example.movieapp.di

import com.example.movieapp.data.remote.MovieRemoteDataSource
import com.example.movieapp.data.remote.MovieRemoteDataSourceImpl
import com.example.movieapp.data.repository.moviedetailsrepo.MovieDetailsrepoImpl
import com.example.movieapp.data.repository.popularmoviesrepo.PopularMoviesrepoImpl
import com.example.movieapp.domain.repository.moviedetailsrepo.IMovieDetailsRepo
import com.example.movieapp.domain.repository.popularmoviesrepo.IPopularMoviesRepo
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
    single<IPopularMoviesRepo> { PopularMoviesrepoImpl(get()) }
    single<IMovieDetailsRepo> { MovieDetailsrepoImpl(get()) }
}