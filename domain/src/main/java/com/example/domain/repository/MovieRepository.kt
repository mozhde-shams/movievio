package com.example.domain.repository

import com.example.domain.model.MovieDetail
import com.example.domain.model.MovieList
import io.reactivex.Single

interface MovieRepository {

    fun getMoviesList(
        movieType: String,
        api_key: String,
        language: String,
        page: Int
    ): Single<MovieList>

    fun getMoviePrimaryInfo(movie_id: Int, api_key: String, language: String): Single<MovieDetail>

    fun getSimilarMovies(
        movie_id: Int,
        api_key: String,
        language: String,
        page: Int
    ): Single<MovieList>

}