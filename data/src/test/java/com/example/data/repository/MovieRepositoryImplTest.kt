package com.example.data.repository

import com.example.data.DataTestData
import com.example.data.entity.MovieDetailEntity
import com.example.data.entity.MovieListEntity
import com.example.data.mapper.MovieEntityMapper
import com.example.data.remote.Remote
import com.example.domain.model.MovieDetail
import com.example.domain.model.MovieList
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {

    @Mock
    private lateinit var remote: Remote

    @Mock
    private lateinit var movieEntityMapper: MovieEntityMapper
    private lateinit var movieRepositoryImpl: MovieRepositoryImpl

    @Before
    fun setUp() {
        movieRepositoryImpl = MovieRepositoryImpl(
            remote,
            movieEntityMapper
        )
    }

    @Test
    fun getMoviesList() {
        val movieListEntity = MovieListEntity(
            DataTestData.resultsOfEntity,
            DataTestData.page,
            DataTestData.total_pages
        )

        val movieList = MovieList(
            DataTestData.results,
            DataTestData.page,
            DataTestData.total_pages
        )

        Mockito.`when`(movieEntityMapper.mapMovieListEntityToDomain(movieListEntity))
            .thenReturn(movieList)

        Mockito.`when`(
            remote.getMoviesList(
                DataTestData.movieType.type,
                DataTestData.api_key,
                DataTestData.language,
                DataTestData.page
            )
        ).thenReturn(Single.just(movieListEntity))

        val movieListObserver = movieRepositoryImpl.getMoviesList(
            DataTestData.movieType.type,
            DataTestData.api_key,
            DataTestData.language,
            DataTestData.page
        )

        Mockito.verify(remote).getMoviesList(
            DataTestData.movieType.type,
            DataTestData.api_key,
            DataTestData.language,
            DataTestData.page
        )

        movieListObserver.test().assertValue {
            it == movieList
        }.onComplete()

    }

    @Test
    fun getMoviePrimaryInfo() {

        val movieDetailEntity = MovieDetailEntity(
            DataTestData.title,
            DataTestData.poster_path,
            DataTestData.overview,
            DataTestData.popularity,
            DataTestData.vote_average,
            DataTestData.release_date,
            DataTestData.genres
        )

        val movieDetail = MovieDetail(
            DataTestData.title,
            DataTestData.poster_path,
            DataTestData.overview,
            DataTestData.popularity,
            DataTestData.vote_average,
            DataTestData.release_date,
            DataTestData.genres
        )

        Mockito.`when`(movieEntityMapper.mapMovieDetailsEntityToDomain(movieDetailEntity))
            .thenReturn(movieDetail)

        Mockito.`when`(
            remote.getMoviePrimaryInfo(
                DataTestData.id,
                DataTestData.api_key,
                DataTestData.language
            )
        ).thenReturn(Single.just(movieDetailEntity))

        val movieDetailObserver = movieRepositoryImpl.getMoviePrimaryInfo(
            DataTestData.id,
            DataTestData.api_key,
            DataTestData.language
        )

        Mockito.verify(
            remote
        ).getMoviePrimaryInfo(
            DataTestData.id,
            DataTestData.api_key,
            DataTestData.language
        )

        movieDetailObserver.test().assertValue {
            it == movieDetail
        }.onComplete()

    }

    @Test
    fun getSimilarMovies() {

        val movieListEntity = MovieListEntity(
            DataTestData.resultsOfEntity,
            DataTestData.page,
            DataTestData.total_pages
        )

        val movieList = MovieList(
            DataTestData.results,
            DataTestData.page,
            DataTestData.total_pages
        )

        Mockito.`when`(movieEntityMapper.mapMovieListEntityToDomain(movieListEntity))
            .thenReturn(movieList)

        Mockito.`when`(
            remote.getSimilarMovies(
                DataTestData.id,
                DataTestData.api_key,
                DataTestData.language,
                DataTestData.page
            )
        ).thenReturn(Single.just(movieListEntity))

        val similarMoviesObserver = movieRepositoryImpl.getSimilarMovies(
            DataTestData.id,
            DataTestData.api_key,
            DataTestData.language,
            DataTestData.page
        )

        Mockito.verify(remote).getSimilarMovies(
            DataTestData.id,
            DataTestData.api_key,
            DataTestData.language,
            DataTestData.page
        )

        similarMoviesObserver.test().assertValue {
            it == movieList
        }.onComplete()

    }

}