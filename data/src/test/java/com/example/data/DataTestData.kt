package com.example.data

import com.example.data.entity.MoviePropertyEntity
import com.example.domain.model.Genre
import com.example.domain.model.MovieProperty
import com.example.domain.usecase.getMovies.MovieType

class DataTestData {

    companion object {
        const val id = 6466
        const val poster_path = "/dkokENeY5Ka30BFgWAqk14mbnGs.jpg"
        const val title = "Xiaomi Mi A3 Android 11, API 30"
        val resultsOfEntity: List<MoviePropertyEntity> = listOf(
            MoviePropertyEntity(
                id,
                poster_path,
                title
            )
        )
        val results: List<MovieProperty> = listOf(
            MovieProperty(
                id,
                poster_path,
                title
            )
        )
        const val page: Int = 0
        const val total_pages: Int = 2

        const val overview: String =
            "Drac tries out some new monster pets to help occupy Tinkles for playtime."
        const val popularity: Double = 759.198
        const val vote_average: Double = 7.6
        const val release_date: String = "2021-05-26"
        private const val name: String = "Horrible"
        val genres: List<Genre> = listOf(Genre(name))
        const val api_key: String = "API Key"
        const val language: String = "en-US"
        val movieType: MovieType = MovieType.POPULAR
    }
}