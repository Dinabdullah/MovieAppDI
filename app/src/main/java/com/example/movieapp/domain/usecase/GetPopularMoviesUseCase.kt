package com.example.movieapp.domain.usecase

import com.example.movieapp.data.model.Movie

interface GetPopularMoviesUseCase {
    suspend operator fun invoke(): Result<List<Movie>>
}