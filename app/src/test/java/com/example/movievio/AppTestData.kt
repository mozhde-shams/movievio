package com.example.movievio

import com.example.domain.model.Genre

class AppTestData {

    companion object {
        const val id = 6466
        const val poster_path = "/dkokENeY5Ka30BFgWAqk14mbnGs.jpg"
        const val title = "Xiaomi Mi A3 Android 11, API 30"
        const val overview: String =
            "Drac tries out some new monster pets to help occupy Tinkles for playtime."
        const val popularity: Double = 759.198
        const val vote_average: Double = 7.6
        const val release_date: String = "2021-05-26"
        private const val name: String = "Horrible"
        val genres: List<Genre> = listOf(Genre(name))
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        const val api_key: String = "API Key"
        const val language: String = "en-US"
        const val pageSize: String = "10"
    }

}