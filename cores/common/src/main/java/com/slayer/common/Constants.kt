package com.slayer.common

object Constants {

    // API SERVICE
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val HEADER_TOKEN_KEY = "Authorization"
    const val HEADER_TOKEN_VALUE = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlN2IzNmZmMmRmYzMzZjk5ZDYyM2RmNTQ2ZmNiZTcyOCIsInN1YiI6IjY2NjE3NmEwYzBhMzNlOGQ0NDdkYzliOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RqIGIoheVQQySFdhAhpw07whDaPVGMChQtrgKGX4Ndw"

    const val ENDPOINT_NOW_PLAYING = "movie/now_playing"
    const val ENDPOINT_POPULAR = "movie/popular"
    const val ENDPOINT_TOP_RATED = "movie/top_rated"
    const val ENDPOINT_UPCOMING = "movie/upcoming"
    const val ENDPOINT_TRENDING = "trending/movie/day"
    const val ENDPOINT_MOVIE_DETAILS = "movie/"

    const val ENDPOINT_MOVIE_CASTS = "movie/{movie_id}/credits"
    const val ENDPOINT_MOVIE_IMAGES = "movie/{movie_id}/images"

    // MOVIES KEYS
    const val NOW_PLAYING = "Now Playing"
    const val POPULAR = "Popular"
    const val TOP_RATED = "Top Rated"
    const val UPCOMING = "Upcoming"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val IMAGE_BASE_URL_HD = "https://image.tmdb.org/t/p/original"
}