package com.example.movieapp.domain.repository

import com.example.movieapp.data.model.MovieResponse

interface MovieRepository {
    suspend fun getPopularMovies(): MovieResponse
}