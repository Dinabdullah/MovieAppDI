package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCaseImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        fetchMovies()
    }

    fun onEvent(event: Events) {
        when (event) {
            is Events.ShowLoading -> {
                showLoading()
            }

            is Events.ShowError -> {
                showError(event.message)
            }

            is Events.FetchMovies -> {
                fetchMovies()
            }
        }
    }

    private fun showLoading() {
        _uiState.value =
            _uiState.value.copy(isLoading = true, errorMessage = null, isOffline = false)
    }

    private fun showError(message: String?) {
        _uiState.value = UIState(isLoading = false, errorMessage = message)
    }

    fun fetchMovies() {
        viewModelScope.launch {
            onEvent(Events.ShowLoading)


            delay(2000)


            val result = getPopularMoviesUseCase()

            result.onSuccess { moviesList ->
                _uiState.value = UIState(
                    isLoading = false,
                    movies = moviesList,
                    isOffline = false,
                )
            }.onFailure { error ->
                _uiState.value = when (error) {
                    is IOException -> _uiState.value.copy(
                        isOffline = true,
                        isLoading = false,
                        errorMessage = null
                    )

                    else -> _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            }
        }
    }


}
