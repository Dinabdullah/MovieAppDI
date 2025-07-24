package com.example.movieapp.domain.usecase

import com.example.movieapp.data.model.MovieResponse
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject


class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): MovieResponse {
        return movieRepository.getPopularMovies()
    }
}