package com.slayer.movie_details.domain.models

data class MovieDetails(
    val id: Int,
    val poster: String,
    val title: String,
    val backgroundImage: String,
    val rating : String,
    val ratingStars : Float,
    val lang: String,
    val genres: List<String>,
    val overview: String,
    val releaseDate : String,
    val movieLength : String
)