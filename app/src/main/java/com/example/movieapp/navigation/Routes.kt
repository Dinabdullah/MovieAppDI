package com.example.movieapp.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Home : Routes()

    @Serializable
    data class Detail(val movieId: Int) : Routes()
}