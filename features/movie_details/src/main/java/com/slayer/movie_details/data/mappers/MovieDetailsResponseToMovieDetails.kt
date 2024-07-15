package com.slayer.movie_details.data.mappers

import com.slayer.common.Constants
import com.slayer.common.utils.formatLanguage
import com.slayer.common.utils.formatRating
import com.slayer.movie_details.domain.models.MovieDetails
import com.slayer.network.dto.movie_details.MovieDetailsResponse


fun MovieDetailsResponse.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        title = originalTitle,
        poster = "${Constants.IMAGE_BASE_URL}$posterPath",
        backgroundImage = "${Constants.IMAGE_BASE_URL_HD}${backdropPath}",
        rating = formatRating(voteAverage),
        lang = formatLanguage(originalLanguage),
        genres = genres.map { it.name },
        overview = overview
    )
}
