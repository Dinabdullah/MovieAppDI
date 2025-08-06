package com.example.movieapp.domain.repository.moviedetailsrepo

import com.example.movieapp.domain.model.Movie

interface IMovieDetailsRepo {
    suspend fun getMovieDetails(movieId: Int): Result<Movie>
}