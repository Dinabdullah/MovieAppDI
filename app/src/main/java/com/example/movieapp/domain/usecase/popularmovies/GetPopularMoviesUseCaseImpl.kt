package com.example.movieapp.domain.usecase.popularmovies

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.popularmoviesrepo.IPopularMoviesRepo


class GetPopularMoviesUseCaseImpl(
    private val popularMovies: IPopularMoviesRepo
) : GetPopularMoviesUseCase {
    override suspend operator fun invoke(): Result<List<Movie>> {
        return popularMovies.getPopularMovies()
    }
}