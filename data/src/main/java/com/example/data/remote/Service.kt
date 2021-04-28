package com.example.data.remote

import com.example.data.entity.MovieDetailEntity
import com.example.data.entity.MovieListEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("movie/{movieType}")
    fun getMoviesList(
        @Path("movieType") movieTye: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieListEntity>

    @GET("movie/{movie_id}")
    fun getMoviePrimaryInfo(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Single<MovieDetailEntity>

    //https://api.themoviedb.org/3/movie/399566/similar?api_key=1eb662d3055651878fc0ef9c66bcd402&language=en-US&page=1
    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ):Single<MovieListEntity>
}