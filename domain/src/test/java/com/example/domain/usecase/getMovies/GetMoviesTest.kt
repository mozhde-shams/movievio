package com.example.domain.usecase.getMovies

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
class GetMoviesTest {

    private lateinit var getMovies: GetMovies
    private lateinit var getMovieListParams: GetMovieListParams

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        getMovies = GetMovies(
            executorThread = testScheduler,
            uiThread = testScheduler,
            movieRepository = movieRepository
        )

        getMovieListParams =
            GetMovieListParams(
                DomainTestData.api_key,
                DomainTestData.language,
                DomainTestData.page,
                DomainTestData.movieType
            )
    }

    @Test
    fun buildUseCaseObservable() {
        getMovies.buildUseCaseObservable(getMovieListParams)
        Mockito.verify(movieRepository).getMoviesList(
            getMovieListParams.movieType.type,
            getMovieListParams.api_key,
            getMovieListParams.language,
            getMovieListParams.page
        )
    }

}