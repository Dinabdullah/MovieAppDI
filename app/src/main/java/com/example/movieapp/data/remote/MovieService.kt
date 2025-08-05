package com.example.movieapp.data.remote

import com.example.movieapp.data.model.MovieDto

interface MovieService {
    suspend fun getPopularMovies(): List<MovieDto>
    suspend fun getMovieDetails(movieId: Int): MovieDto
}
