package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.Data
import com.example.movieapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<Data>>(emptyList())
    val movies: StateFlow<List<Data>> = _movies

    fun fetchMovies() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getMovies()
            _movies.value = response.movies
        }
    }

//    fun fetchMovies() {
//        viewModelScope.launch {
//            val randomPage = (1..20).random()
//            val response = RetrofitInstance.api.getMoviesByPage(randomPage)
//            _movies.value = response.movies
//        }
//    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            val response = RetrofitInstance.api.searchMovies(query)
            _movies.value = response.movies
        }
    }

}
