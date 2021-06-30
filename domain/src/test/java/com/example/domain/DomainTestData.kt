package com.example.domain

import com.example.domain.usecase.getMovies.MovieType

class DomainTestData {

    companion object {
        const val movie_id: Int = 6466
        const val api_key: String = "API Key"
        const val language: String = "en-US"
        const val page: Int = 1
        val movieType: MovieType = MovieType.POPULAR
    }

}