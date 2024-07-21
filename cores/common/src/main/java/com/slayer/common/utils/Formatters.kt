package com.slayer.common.utils

import com.slayer.common.Constants
import java.util.Locale

fun formatRating(rating: Double): String {
    return String.format(Locale.getDefault(), "%.1f", rating)
}

fun formatLanguage(lang : String) : String {
    return lang.replace(lang[0], lang[0].uppercaseChar())
}

fun formatRuntime(runtime : Int) : String {
    val hours = runtime / 60
    val minutes = runtime % 60
    return "${hours}h ${minutes}m"
}

fun formatReleaseDate(date : String) : String {
    val parts = date.split("-")
    return "${parts[2]}/${parts[1]}/${parts[0]}"
}

fun formatMovieUrlWithIdPath(id : Int, endpoint : String) : String {
    return endpoint.replace("{movie_id}", id.toString())
}

fun formatPosterUrlWithPath(isHighQuality : Boolean, path : String?) : String {
    return if (isHighQuality)  {
        "${Constants.IMAGE_BASE_URL_HD}${path}"
    } else {
        "${Constants.IMAGE_BASE_URL}${path}"
    }
}