package com.slayer.network.dto.movies


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    @SerialName("maximum")
    val maximum: String,
    @SerialName("minimum")
    val minimum: String
)