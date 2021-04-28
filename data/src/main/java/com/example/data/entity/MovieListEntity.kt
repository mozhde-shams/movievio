package com.example.data.entity

data class MovieListEntity(
    val results: List<MoviePropertyEntity>,
    val page: Int,
    val total_pages: Int
)