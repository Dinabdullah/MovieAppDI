package com.example.movieapp.domain.usecase.popularmovies

import com.example.movieapp.domain.model.Movie


interface GetPopularMoviesUseCase {
    suspend operator fun invoke(): Result<List<Movie>>
}