package com.example.movieapp.domain.usecase.popularmovies

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.popularmovies.PopularMovies


class GetPopularMoviesUseCaseImpl(
    private val popularMovies: PopularMovies
) : GetPopularMoviesUseCase {
    override suspend operator fun invoke(): Result<List<Movie>> {
        return popularMovies.getPopularMovies()
    }
}