package com.slayer.common_ui

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    object Discovery

    @Serializable
    data class MovieDetailsArgs(
        val id : Int
    )
}