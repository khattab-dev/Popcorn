package com.slayer.discovery.data.mappers

import com.slayer.common.utils.formatLanguage
import com.slayer.common.utils.formatPosterUrlWithPath
import com.slayer.common.utils.formatRating
import com.slayer.discovery.domain.models.Movie
import com.slayer.network.dto.movies.MoviesResponse

fun MoviesResponse.toMovies() : List<Movie> {
    return this.movieResults.map { result ->
        Movie(
            id = result.id,
            title = result.title,
            poster = formatPosterUrlWithPath(isHighQuality = false, result.posterPath),
            rating = formatRating(result.voteAverage),
            lang = formatLanguage(result.originalLanguage),
            releaseDate = result.releaseDate,
            genresIds = result.genreIds,
            backdrop = formatPosterUrlWithPath(false,result.backdropPath),

        )
    }
}