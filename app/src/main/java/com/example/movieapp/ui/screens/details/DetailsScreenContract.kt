package com.example.movieapp.ui.screens.details

import com.example.movieapp.domain.model.Movie


sealed class MovieDetailsState {
    data object IsOffline : MovieDetailsState()
    data object Idle : MovieDetailsState()
    data class OnError(val msgRes: Int) : MovieDetailsState()
    data class OnSuccess(val movie: Movie) : MovieDetailsState()
}

sealed class MovieDetailsEvents {
    data class FetchMovie(val movieId: Int) : MovieDetailsEvents()
}

