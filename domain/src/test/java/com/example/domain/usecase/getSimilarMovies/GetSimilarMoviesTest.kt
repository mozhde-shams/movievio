package com.example.domain.usecase.getSimilarMovies

import com.example.domain.DomainTestData
import com.example.domain.repository.MovieRepository
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetSimilarMoviesTest {

    private lateinit var getSimilarMovies: GetSimilarMovies
    private lateinit var getSimilarMoviesParams: GetSimilarMoviesParams

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        getSimilarMovies = GetSimilarMovies(
            executorThread = testScheduler,
            uiThread = testScheduler,
            movieRepository = movieRepository
        )

        getSimilarMoviesParams =
            GetSimilarMoviesParams(
                DomainTestData.movie_id,
                DomainTestData.api_key,
                DomainTestData.language,
                DomainTestData.page
            )
    }

    @Test
    fun buildUseCaseObservable() {
        getSimilarMovies.buildUseCaseObservable(getSimilarMoviesParams)
        Mockito.verify(movieRepository).getSimilarMovies(
            getSimilarMoviesParams.movie_id,
            getSimilarMoviesParams.api_key,
            getSimilarMoviesParams.language,
            getSimilarMoviesParams.page
        )
    }

}