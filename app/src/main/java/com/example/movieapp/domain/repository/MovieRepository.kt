package com.example.movieapp.domain.repository

import com.example.movieapp.data.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): Result<List<Movie>>
}