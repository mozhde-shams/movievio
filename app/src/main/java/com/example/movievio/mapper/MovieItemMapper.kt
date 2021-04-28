package com.example.movievio.mapper

import com.example.domain.model.MovieDetail
import com.example.domain.model.MovieProperty
import com.example.movievio.model.MovieDetailItem
import com.example.movievio.model.MovieItem
import javax.inject.Inject

class MovieItemMapper @Inject constructor() {

    fun mapMoviePropertyItemToPresentation(movieProperty: MovieProperty): MovieItem {
        return MovieItem(
            movieProperty.id,
            IMAGE_BASE_URL.plus(movieProperty.poster_path),
            movieProperty.title
        )
    }

    fun mapMovieDetailToPresentation(movieDetail: MovieDetail): MovieDetailItem {
        return MovieDetailItem(
            movieDetail.title,
            IMAGE_BASE_URL.plus(movieDetail.poster_path),
            movieDetail.overview,
            movieDetail.popularity,
            movieDetail.vote_average,
            movieDetail.release_date,
            movieDetail.genres
        )
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }

}