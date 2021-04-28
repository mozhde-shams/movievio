package com.example.data.repository

import com.example.data.mapper.MovieEntityMapper
import com.example.data.remote.Remote
import com.example.domain.model.MovieDetail
import com.example.domain.model.MovieList
import com.example.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remote: Remote,
    private val movieEntityMapper: MovieEntityMapper
) : MovieRepository {

    override fun getMoviesList(
        movieType: String,
        api_key: String,
        language: String,
        page: Int
    ): Single<MovieList> {
        return remote.getMoviesList(movieType, api_key, language, page).map {
            movieEntityMapper.mapMovieListEntityToDomain(it)
        }
    }

    override fun getMoviePrimaryInfo(
        movie_id: Int,
        api_key: String,
        language: String
    ): Single<MovieDetail> {
        return remote.getMoviePrimaryInfo(movie_id, api_key, language)
            .map { movieEntityMapper.mapMovieDetailsEntityToDomain(it) }
    }

    override fun getSimilarMovies(
        movie_id: Int,
        api_key: String,
        language: String,
        page: Int
    ): Single<MovieList> {
        return remote.getSimilarMovies(movie_id, api_key, language, page)
            .map { movieEntityMapper.mapMovieListEntityToDomain(it) }
    }

}