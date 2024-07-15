package com.slayer.common.utils

import java.util.Locale

fun formatRating(rating: Double): String {
    return String.format(Locale.getDefault(), "%.1f", rating)
}

fun formatLanguage(lang : String) : String {
    return lang.replace(lang[0], lang[0].uppercaseChar())
}
