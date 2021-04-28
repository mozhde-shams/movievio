package com.example.domain.usecase.getMovieDetails


data class GetMovieDetailsParams(
    val movie_id: Int,
    val api_key: String,
    val language: String
)
