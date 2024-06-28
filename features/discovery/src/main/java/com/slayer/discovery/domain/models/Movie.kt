package com.slayer.discovery.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val rating: Double,
    val lang: String,
    val desc : String,
)
