package com.example.movieapp.ui.screens.home

import com.example.movieapp.domain.model.Movie


sealed class UIState {
    data object IsLoading : UIState()
    data object IsOffline : UIState()
    data class OnError(val msg: Int) : UIState()
    data class MoviesFetched(val list: List<Movie>) : UIState()

}

sealed class Events {
    object FetchMovies : Events()
}
