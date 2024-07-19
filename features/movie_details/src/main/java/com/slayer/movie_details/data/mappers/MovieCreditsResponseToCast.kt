package com.slayer.movie_details.data.mappers

import com.slayer.common.Constants
import com.slayer.common.utils.formatLanguage
import com.slayer.common.utils.formatRating
import com.slayer.movie_details.domain.models.Cast
import com.slayer.movie_details.domain.models.MovieDetails
import com.slayer.network.dto.movie_credits.MovieCreditsResponse
import com.slayer.network.dto.movie_details.MovieDetailsResponse

fun MovieCreditsResponse.toCast(): List<Cast> {
    return cast.map {
        Cast(
            id = it.id,
            name = it.name,
            image = "${Constants.IMAGE_BASE_URL}${it.profilePath}",
            isMale = it.gender == 0
        )
    }
}
