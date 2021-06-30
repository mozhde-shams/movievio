package com.example.domain.usecase.getMovieDetails

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
class GetMovieDetailTest {

    private lateinit var getMovieDetails: GetMovieDetails
    private lateinit var getMovieDetailsParams: GetMovieDetailsParams

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setUp() {
        getMovieDetails = GetMovieDetails(
            executorThread = testScheduler,
            uiThread = testScheduler,
            movieRepository = movieRepository
        )

        getMovieDetailsParams =
            GetMovieDetailsParams(
                DomainTestData.movie_id,
                DomainTestData.api_key,
                DomainTestData.language
            )
    }

    @Test
    fun buildUseCaseObservable() {
        getMovieDetails.buildUseCaseObservable(getMovieDetailsParams)
        Mockito.verify(movieRepository).getMoviePrimaryInfo(
            getMovieDetailsParams.movie_id,
            getMovieDetailsParams.api_key,
            getMovieDetailsParams.language
        )
    }

}