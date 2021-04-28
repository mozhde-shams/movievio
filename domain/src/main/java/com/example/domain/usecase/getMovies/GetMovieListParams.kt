package com.example.domain.usecase.getMovies

enum class MovieType(val type: String) {
    POPULAR("popular"),
    TOPRATED("top_rated"),
    NOWPLAYING("now_playing"),
    UPCOMING("upcoming")
}

data class GetMovieListParams(
    val api_key: String,
    val language: String,
    val page: Int,
    val movieType: MovieType
)