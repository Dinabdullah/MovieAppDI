package com.example.movieapp.data.remote

import com.example.movieapp.data.model.MovieResponse
import retrofit2.http.GET


interface MovieApi {
    @GET("movie/popular")
    suspend fun getMovies(): MovieResponse

}