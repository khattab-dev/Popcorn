package com.slayer.movie_details.data.mappers

import com.slayer.common.Constants
import com.slayer.common.utils.formatPosterUrlWithPath
import com.slayer.network.dto.movie_images.MovieImagesResponse

fun MovieImagesResponse.toMovieBackdrops(): List<String> {
    return backdrops.map { formatPosterUrlWithPath(isHighQuality = true, it.filePath) }
}