package com.slayer.core.common_ui.models

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
) {

    companion object {
        private val movie = Movie(0, "Movie 1", "https://image.tmdb.org/t/p/w500/qZPLK5ktRKa3CL4sKRZtj8UlPYc.jpg")

        fun getMovies() : List<Movie> {
            val movies = mutableListOf<Movie>()

            repeat(5) {
                movies.add(movie.copy(id = it, title = "Movie $it"))
            }

            return movies
        }

        fun getPopularMovies() : List<Movie> {
            val movies = mutableListOf<Movie>()

            repeat(5) {
                movies.add(movie.copy(id = it, title = "Movie $it", poster = "https://image.tmdb.org/t/p/w500/nP6RliHjxsz4irTKsxe8FRhKZYl.jpg"))
            }

            return movies
        }

        fun getTopRated() : List<Movie> {
            val movies = mutableListOf<Movie>()

            repeat(5) {
                movies.add(movie.copy(id = it, title = "Movie $it", poster = "https://image.tmdb.org/t/p/w500/qKkaG7HVFVe7C1JuxTGwNz0eSyL.jpg"))
            }

            return movies
        }

        fun getUpcoming() : List<Movie> {
            val movies = mutableListOf<Movie>()

            repeat(5) {
                movies.add(movie.copy(id = it, title = "Movie $it", poster = "https://image.tmdb.org/t/p/w500/etbHJxil0wHvYOCmibzFLsMcl2C.jpg"))
            }

            return movies
        }

        val movieMap = mapOf(
            "Now Playing" to getMovies(),
            "Popular" to getPopularMovies(),
            "Top Rated" to getTopRated(),
            "Upcoming" to getUpcoming()
        )
    }
}
