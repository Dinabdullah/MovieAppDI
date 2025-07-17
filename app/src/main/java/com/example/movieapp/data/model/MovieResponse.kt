package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
//    val `data`: List<Data>,
    @SerializedName("data")
    val movies: List<Data>,
    val metadata: Metadata
)