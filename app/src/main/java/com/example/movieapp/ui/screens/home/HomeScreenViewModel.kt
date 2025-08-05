package com.example.movieapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.R
import com.example.movieapp.domain.usecase.popularmovies.GetPopularMoviesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class HomeScreenViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.IsLoading)
    val uiState: StateFlow<UIState> = _uiState

    init {
        fetchMovies()
    }

    fun onEvent(event: Events) {
        when (event) {

            is Events.FetchMovies -> {
                fetchMovies()
            }

        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {

            _uiState.value = UIState.IsLoading


            delay(2000)


            val result = getPopularMoviesUseCase()

            result.onSuccess { moviesList ->
                _uiState.value = UIState.MoviesFetched(moviesList)
            }.onFailure { error ->
                _uiState.value = when (error) {
                    is IOException -> UIState.IsOffline
                    else -> UIState.OnError(R.string.something_went_wrong)
                }
            }

        }

    }


}