package com.example.movieapp.viewmodel

sealed class Events {
    object ShowLoading : Events()
    data class ShowError(val message: String?) : Events()
    object FetchMovies : Events()
}
