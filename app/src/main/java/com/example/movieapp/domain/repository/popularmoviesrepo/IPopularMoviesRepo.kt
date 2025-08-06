package com.example.movieapp.domain.repository.popularmoviesrepo

import com.example.movieapp.domain.model.Movie

interface IPopularMoviesRepo {
    suspend fun getPopularMovies(): Result<List<Movie>>
}