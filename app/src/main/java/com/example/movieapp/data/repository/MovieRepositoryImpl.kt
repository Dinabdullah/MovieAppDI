package com.example.movieapp.data.repository

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.domain.repository.MovieRepository
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getPopularMovies(): Result<List<Movie>> {
        return try {
            val response = api.getMovies()
            Result.success(response.results?.filterNotNull() ?: emptyList())
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

