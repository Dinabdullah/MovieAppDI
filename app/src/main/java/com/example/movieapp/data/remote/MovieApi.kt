package com.example.movieapp.data.remote

import com.example.movieapp.common.Constants
import com.example.movieapp.data.model.MovieDto
import com.example.movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET(Constants.Endpoints.POPULAR_MOVIES)
    suspend fun getMovies(): MovieResponse

    @GET(Constants.Endpoints.MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path(Constants.MOVIE_ID) movieId: Int): MovieDto

}