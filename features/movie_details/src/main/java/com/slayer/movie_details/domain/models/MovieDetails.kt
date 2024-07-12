package com.slayer.movie_details.domain.models

data class MovieDetails(
    val id: Int,
    val poster: String,
    val title: String,
    val backgroundImage: String,
    val rating : Double
)