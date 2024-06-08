package com.slayer.discovery.data.mappers

import com.slayer.discovery.domain.models.Movie
import com.slayer.network.dto.MoviesResponse

fun MoviesResponse.toMovies() : List<Movie> {
    return this.movieResults.map { result ->
        Movie(
            id = result.id,
            title = result.title,
            poster = "https://image.tmdb.org/t/p/w500/${result.posterPath}"
        )
    }
}