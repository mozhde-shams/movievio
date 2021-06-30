package com.example.movievio.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.MovieDetail
import com.example.domain.usecase.getMovieDetails.GetMovieDetails
import com.example.domain.usecase.getMovieDetails.GetMovieDetailsParams
import com.example.domain.usecase.getSimilarMovies.GetSimilarMovies
import com.example.movievio.AppTestData
import com.example.movievio.mapper.MovieItemMapper
import com.example.movievio.model.MovieDetailItem
import com.example.movievio.movieDetail.MovieDetailViewModel
import io.reactivex.SingleObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @Mock
    lateinit var getMovieDetails: GetMovieDetails

    @Mock
    lateinit var movieItemMapper: MovieItemMapper

    @Mock
    lateinit var getSimilarMovies: GetSimilarMovies

    private lateinit var fakeMovieDetail: MovieDetail
    private lateinit var fakeMovieDetailsItem: MovieDetailItem
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        fakeMovieDetail = MovieDetail(
            AppTestData.title,
            AppTestData.poster_path,
            AppTestData.overview,
            AppTestData.popularity,
            AppTestData.vote_average,
            AppTestData.release_date,
            AppTestData.genres
        )

        fakeMovieDetailsItem = MovieDetailItem(
            AppTestData.title,
            AppTestData.poster_path,
            AppTestData.overview,
            AppTestData.popularity,
            AppTestData.vote_average,
            AppTestData.release_date,
            AppTestData.genres
        )

    }

    @Test
    fun getMovieDetails() {
        Mockito.`when`(movieItemMapper.mapMovieDetailToPresentation(any()))
            .thenReturn(fakeMovieDetailsItem)

        val movieDetailViewModel = MovieDetailViewModel(
            getMovieDetails,
            movieItemMapper,
            getSimilarMovies,
            AppTestData.api_key,
            AppTestData.language,
            AppTestData.pageSize
        )

        movieDetailViewModel.getMovieDetails(
            AppTestData.id,
            AppTestData.api_key,
            AppTestData.language
        )

        val getMovieDetailParamsCaptor: ArgumentCaptor<GetMovieDetailsParams> =
            ArgumentCaptor.forClass(GetMovieDetailsParams::class.java)
        val getMovieDetailObserverCaptor: ArgumentCaptor<SingleObserver<MovieDetail>> =
            ArgumentCaptor.forClass(SingleObserver::class.java) as ArgumentCaptor<SingleObserver<MovieDetail>>

        Mockito.verify(getMovieDetails)
            .execute(capture(getMovieDetailObserverCaptor), capture(getMovieDetailParamsCaptor))
        getMovieDetailObserverCaptor.value.onSuccess(fakeMovieDetail)


        Assert.assertEquals(
            fakeMovieDetail.title,
            movieDetailViewModel.movieTitleLiveData.value
        )

        Assert.assertEquals(
            fakeMovieDetail.poster_path,
            movieDetailViewModel.moviePosterPathLiveData.value
        )

        Assert.assertEquals(
            fakeMovieDetail.overview,
            movieDetailViewModel.movieOverviewLiveData.value
        )

        Assert.assertEquals(
            fakeMovieDetail.popularity,
            movieDetailViewModel.moviePopularityLiveData.value
        )

        Assert.assertEquals(
            fakeMovieDetail.vote_average,
            movieDetailViewModel.movieVoteAverageLiveData.value
        )

        Assert.assertEquals(
            fakeMovieDetail.release_date,
            movieDetailViewModel.movieReleaseDateLiveData.value
        )

        Assert.assertEquals(
            fakeMovieDetail.genres.joinToString {
                it.name
            },
            movieDetailViewModel.movieGenresLiveData.value
        )


    }


    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    private fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

}