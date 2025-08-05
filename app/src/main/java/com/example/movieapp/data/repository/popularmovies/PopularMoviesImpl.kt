package com.example.movieapp.data.repository.popularmovies

import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.data.remote.MovieService
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.popularmovies.PopularMovies
import java.io.IOException

class PopularMoviesImpl(
    private val movieService: MovieService
) : PopularMovies {
    override suspend fun getPopularMovies(): Result<List<Movie>> {
        return try {
            val response = movieService.getPopularMovies()
            val domainMovies = response.map { it.toDomain() }
            Result.success(domainMovies)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
