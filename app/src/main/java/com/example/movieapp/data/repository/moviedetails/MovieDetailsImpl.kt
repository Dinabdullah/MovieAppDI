package com.example.movieapp.data.repository.moviedetails

import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.remote.MovieService
import com.example.movieapp.domain.repository.moviedetails.MovieDetails
import com.example.movieapp.domain.model.Movie
import java.io.IOException

class MovieDetailsImpl(
    private val movieService: MovieService
) : MovieDetails {
    override suspend fun getMovieDetails(movieId: Int): Result<Movie> {
        return try {
            val movieDto = movieService.getMovieDetails(movieId)
            Result.success(movieDto.toDomain())
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}