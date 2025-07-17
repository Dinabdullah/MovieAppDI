package com.example.movieapp.data.remote

import com.example.movieapp.data.model.Data
import com.example.movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movies")
    suspend fun getMovies(): MovieResponse

    @GET("movies")
    suspend fun getMoviesByPage(@Query("page") page: Int): MovieResponse

    @GET("movies/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): Data

    @GET("movies")
    suspend fun searchMovies(@Query("q") query: String): MovieResponse


}