package com.slayer.common.utils

import java.util.Locale

fun formatRating(rating : Double) : Double {
    return String.format(locale = Locale.getDefault(),"%.1f", rating).toDouble()
}