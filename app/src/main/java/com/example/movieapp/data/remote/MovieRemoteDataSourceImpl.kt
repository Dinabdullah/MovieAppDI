package com.example.movieapp.data.remote

import com.example.movieapp.data.model.MovieDto

class MovieRemoteDataSourceImpl(
    private val api: MovieApi
) : MovieRemoteDataSource {
    override suspend fun getPopularMovies(): List<MovieDto> {
        return api.getMovies().results?.filterNotNull() ?: emptyList()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDto {
        return api.getMovieDetails(movieId)
    }
}
