package com.example.movieapp.domain.repository.moviedetails

import com.example.movieapp.domain.model.Movie

interface MovieDetails {
    suspend fun getMovieDetails(movieId: Int): Result<Movie>
}