package com.example.movieapp.data.repository

import com.example.movieapp.data.model.MovieResponse
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovies(): MovieResponse {
        return api.getMovies()
    }
}