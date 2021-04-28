package com.example.data.remote

import com.example.data.entity.MovieDetailEntity
import com.example.data.entity.MovieListEntity
import io.reactivex.Single

class Remote(private val serviceGenerator: ServiceGenerator) {

    fun getMoviesList(
        movieType: String,
        api_key: String,
        language: String,
        page: Int
    ): Single<MovieListEntity> {
        return serviceGenerator.service().getMoviesList(movieType, api_key, language, page)
    }

    fun getMoviePrimaryInfo(
        movie_id: Int,
        api_key: String,
        language: String
    ): Single<MovieDetailEntity> {
        return serviceGenerator.service().getMoviePrimaryInfo(movie_id, api_key, language)
    }

    fun getSimilarMovies(
        movie_id: Int,
        api_key: String,
        language: String,
        page: Int
    ): Single<MovieListEntity> {
        return serviceGenerator.service().getSimilarMovies(movie_id, api_key, language, page)
    }
}