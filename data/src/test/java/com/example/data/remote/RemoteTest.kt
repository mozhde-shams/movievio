package com.example.data.remote

import com.example.data.DataTestData
import com.example.data.entity.MovieDetailEntity
import com.example.data.entity.MovieListEntity
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteTest {

    private lateinit var remote: Remote

    @Mock
    private lateinit var serviceGenerator: ServiceGenerator

    @Mock
    private lateinit var service: Service

    @Before
    fun setUp() {
        remote = Remote(serviceGenerator)
    }

    @Test
    fun getMoviesList() {
        val movieListEntity = MovieListEntity(
            DataTestData.resultsOfEntity,
            DataTestData.page,
            DataTestData.total_pages
        )

        Mockito.`when`(serviceGenerator.service()).thenReturn(service)
        Mockito.`when`(
            service.getMoviesList(
                DataTestData.movieType.type,
                DataTestData.api_key,
                DataTestData.language,
                DataTestData.page
            )
        ).thenReturn(Single.just(movieListEntity))


        val singleMovieListEntity = remote.getMoviesList(
            DataTestData.movieType.type,
            DataTestData.api_key,
            DataTestData.language,
            DataTestData.page
        )

        singleMovieListEntity.test().assertValue {
            it == movieListEntity
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
        Mockito.`when`(serviceGenerator.service()).thenReturn(service)
        Mockito.`when`(
            service.getMoviePrimaryInfo(
                DataTestData.id,
                DataTestData.api_key,
                DataTestData.language
            )
        ).thenReturn(Single.just(movieDetailEntity))

        val singleMovieDetailEntity = remote.getMoviePrimaryInfo(
            DataTestData.id,
            DataTestData.api_key,
            DataTestData.language
        )

        singleMovieDetailEntity.test().assertValue {
            it == movieDetailEntity
        }.onComplete()
    }

    @Test
    fun getSimilarMovies() {
        val similarMoviesEntity = MovieListEntity(
            DataTestData.resultsOfEntity,
            DataTestData.page,
            DataTestData.total_pages
        )

        Mockito.`when`(serviceGenerator.service()).thenReturn(service)
        Mockito.`when`(
            service.getSimilarMovies(
                DataTestData.id,
                DataTestData.api_key,
                DataTestData.language,
                DataTestData.page
            )
        ).thenReturn(Single.just(similarMoviesEntity))

        val singleSimilarMovies = remote.getSimilarMovies(
            DataTestData.id,
            DataTestData.api_key,
            DataTestData.language,
            DataTestData.page
        )

        singleSimilarMovies.test().assertValue {
            it == similarMoviesEntity
        }.onComplete()

    }

}