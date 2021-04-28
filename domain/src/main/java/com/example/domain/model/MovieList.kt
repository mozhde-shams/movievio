package com.example.domain.model

data class MovieList(
    val results: List<MovieProperty>,
    val page: Int,
    val total_pages: Int
)