package com.example.movieapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.Data
import com.example.movieapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val _movie = MutableStateFlow<Data?>(null)
    val movie: StateFlow<Data?> = _movie
    val isFavorite =  mutableStateOf(false)


    fun fetchMovieById(id: Int) {
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.getMovieDetails(id)
                _movie.value = result
            } catch (e: Exception) {
                Log.e("DetailsViewModel", "Error: ${e.message}")
            }
        }
    }


}