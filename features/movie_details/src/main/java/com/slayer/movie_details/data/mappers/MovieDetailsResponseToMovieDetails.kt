package com.slayer.movie_details.data.mappers

import com.slayer.movie_details.domain.models.MovieDetails
import com.slayer.network.dto.movie_details.MovieDetailsResponse


fun MovieDetailsResponse.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        title = originalTitle,
    )
}
