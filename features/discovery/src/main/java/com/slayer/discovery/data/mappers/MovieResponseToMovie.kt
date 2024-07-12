package com.slayer.discovery.data.mappers

import com.slayer.common.Constants
import com.slayer.common.utils.formatRating
import com.slayer.discovery.domain.models.Movie
import com.slayer.network.dto.movies.MoviesResponse
import java.util.Locale

fun MoviesResponse.toMovies() : List<Movie> {
    return this.movieResults.map { result ->
        Movie(
            id = result.id,
            title = result.title,
            poster = "${Constants.IMAGE_BASE_URL}${result.posterPath}",
            rating = formatRating(result.voteAverage),
            lang = result.originalLanguage.replace(result.originalLanguage[0], result.originalLanguage[0].uppercaseChar()),
            releaseDate = result.releaseDate,
            genresIds = result.genreIds
        )
    }
}