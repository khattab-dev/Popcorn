package com.slayer.network.dto.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerialName("dates")
    val dates: Dates? = null,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val movieResults: List<MovieResult>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)