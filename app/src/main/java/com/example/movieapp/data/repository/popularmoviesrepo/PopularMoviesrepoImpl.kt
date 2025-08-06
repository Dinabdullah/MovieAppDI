package com.example.movieapp.data.repository.popularmoviesrepo

import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.data.remote.MovieRemoteDataSource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.popularmoviesrepo.IPopularMoviesRepo
import java.io.IOException

class PopularMoviesrepoImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : IPopularMoviesRepo {
    override suspend fun getPopularMovies(): Result<List<Movie>> {
        return try {
            val response = movieRemoteDataSource.getPopularMovies()
            val domainMovies = response.map { it.toDomain() }
            Result.success(domainMovies)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
