package com.example.movieapp.domain.usecase.moviedetails

import com.example.movieapp.domain.model.Movie

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(movieId: Int): Result<Movie>
}