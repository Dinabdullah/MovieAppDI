package com.example.movieapp.data.mapper

import com.example.movieapp.data.model.MovieDto
import com.example.movieapp.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = this.id ?: 0,
        title = this.title.orEmpty(),
        overview = this.overview.orEmpty(),
        posterPath = "https://image.tmdb.org/t/p/w500${this.posterPath.orEmpty()}"
    )
}