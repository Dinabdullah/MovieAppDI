package com.example.movieapp.domain.usecase

import com.example.movieapp.data.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl
@Inject constructor(private val movieRepository: MovieRepository) :
    GetPopularMoviesUseCase {
    override suspend operator fun invoke(): Result<List<Movie>> {
        return movieRepository.getPopularMovies()
    }
}
