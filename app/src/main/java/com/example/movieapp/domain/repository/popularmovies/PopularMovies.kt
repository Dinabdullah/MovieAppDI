package com.example.movieapp.domain.repository.popularmovies

import com.example.movieapp.domain.model.Movie

interface PopularMovies {
    suspend fun getPopularMovies(): Result<List<Movie>>
}