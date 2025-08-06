package com.example.movieapp.data.repository.moviedetailsrepo

import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.data.remote.MovieRemoteDataSource
import com.example.movieapp.domain.repository.moviedetailsrepo.IMovieDetailsRepo
import com.example.movieapp.domain.model.Movie
import java.io.IOException

class MovieDetailsrepoImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : IMovieDetailsRepo {
    override suspend fun getMovieDetails(movieId: Int): Result<Movie> {
        return try {
            val movieDto = movieRemoteDataSource.getMovieDetails(movieId)
            Result.success(movieDto.toDomain())
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}