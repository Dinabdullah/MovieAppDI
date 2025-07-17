package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieapp.data.model.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavouriteViewModel : ViewModel() {
    private val _favouriteMovies = MutableStateFlow<List<Data>>(emptyList())
    val favouriteMovies: StateFlow<List<Data>> = _favouriteMovies

    fun addToFavourites(movie: Data) {
        _favouriteMovies.value = _favouriteMovies.value + movie
    }

    fun removeFromFavourites(movie: Data) {
        _favouriteMovies.value = _favouriteMovies.value.filter { it.id != movie.id }
    }
}
