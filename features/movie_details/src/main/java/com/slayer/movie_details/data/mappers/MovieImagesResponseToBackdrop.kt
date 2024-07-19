package com.slayer.movie_details.data.mappers

import com.slayer.common.Constants
import com.slayer.network.dto.movie_images.MovieImagesResponse

fun MovieImagesResponse.toMovieBackdrops(): List<String> {
    return backdrops.map { "${Constants.IMAGE_BASE_URL_HD}${it.filePath}" }
}