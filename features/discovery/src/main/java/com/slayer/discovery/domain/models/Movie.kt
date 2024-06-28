package com.slayer.discovery.domain.models

import androidx.compose.runtime.Immutable

@Immutable
data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val rating: Double,
    val lang: String,
    val releaseDate: String,
    val genresIds: List<Int>
)
