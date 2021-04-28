package com.example.domain.usecase.getSimilarMovies

data class GetSimilarMoviesParams(
    val movie_id: Int,
    val api_key: String,
    val language: String,
    val page: Int
)