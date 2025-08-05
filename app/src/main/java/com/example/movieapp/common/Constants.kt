package com.example.movieapp.common

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY_VALUE = "372563c8460f7a54e4d9fc1c87b3c246"

    const val API_KEY = "api_key"
    const val MOVIE_ID = "movie_id"

    object Endpoints {
        const val POPULAR_MOVIES = "movie/popular"
        const val MOVIE_DETAILS = "movie/{movie_id}"
    }
}
