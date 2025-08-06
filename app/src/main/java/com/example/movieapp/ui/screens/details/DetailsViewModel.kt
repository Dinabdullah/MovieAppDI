package com.example.movieapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.R
import com.example.movieapp.domain.usecase.moviedetails.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _detailsState: MutableStateFlow<MovieDetailsState> =
        MutableStateFlow(MovieDetailsState.Idle)
    val detailsState: StateFlow<MovieDetailsState> = _detailsState

    fun onEvent(event: MovieDetailsEvents) {
        when (event) {
            is MovieDetailsEvents.FetchMovie -> fetchMovies(event.movieId)
        }
    }

    private fun fetchMovies(movieId: Int) {
        viewModelScope.launch {
            val result = getMovieDetailsUseCase(movieId)

            result
                .onSuccess { movie ->
                    _detailsState.value = MovieDetailsState.OnSuccess(movie)
                }
                .onFailure { error ->
                    val state = when (error) {
                        is IOException -> MovieDetailsState.IsOffline
                        else -> {
                            val resId =
                                R.string.something_went_wrong
                            MovieDetailsState.OnError(resId)
                        }
                    }
                    _detailsState.value = state
                }
        }
    }


}