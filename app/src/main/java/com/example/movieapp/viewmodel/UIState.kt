package com.example.movieapp.viewmodel

import com.example.movieapp.data.model.Movie

data class UIState(
    val isLoading: Boolean = false,
    val isOffline: Boolean = false,
    val errorMessage: String? = null,
    val movies: List<Movie> = emptyList()
)
