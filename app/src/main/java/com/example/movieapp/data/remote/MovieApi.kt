package com.example.movieapp.data.remote

import com.example.movieapp.common.Constants
import com.example.movieapp.data.model.MovieResponse
import retrofit2.http.GET


interface MovieApi {
    @GET(Constants.Endpoints.POPULAR_MOVIES)
    suspend fun getMovies(): MovieResponse

}