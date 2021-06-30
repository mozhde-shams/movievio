package com.example.data.mapper

import com.example.data.entity.MovieDetailEntity
import com.example.data.entity.MoviePropertyEntity
import com.example.data.entity.MovieListEntity
import com.example.domain.model.MovieDetail
import com.example.domain.model.MovieProperty
import com.example.domain.model.MovieList
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() {

    fun mapMovieListEntityToDomain(movieListEntity: MovieListEntity) =
        MovieList(
            movieListEntity.results.map { mapMoviePropertyEntityToDomain(it) },
            movieListEntity.page,
            movieListEntity.total_pages
        )

     fun mapMoviePropertyEntityToDomain(moviePropertyEntity: MoviePropertyEntity) =
        MovieProperty(
            moviePropertyEntity.id,
            moviePropertyEntity.poster_path,
            moviePropertyEntity.title
        )

    fun mapMovieDetailsEntityToDomain(movieDetailEntity: MovieDetailEntity) =
        MovieDetail(
            movieDetailEntity.title,
            movieDetailEntity.poster_path,
            movieDetailEntity.overview,
            movieDetailEntity.popularity,
            movieDetailEntity.vote_average,
            movieDetailEntity.release_date,
            movieDetailEntity.genres
        )

}