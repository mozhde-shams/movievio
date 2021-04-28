package com.example.movievio.movieDetail

import androidx.lifecycle.*
import androidx.paging.*
import com.example.domain.model.MovieDetail
import com.example.domain.usecase.base.DefaultSingleObserver
import com.example.domain.usecase.getMovieDetails.GetMovieDetails
import com.example.domain.usecase.getMovieDetails.GetMovieDetailsParams
import com.example.domain.usecase.getSimilarMovies.GetSimilarMovies
import com.example.movievio.mapper.MovieItemMapper
import com.example.movievio.model.MovieItem
import com.example.movievio.movieDetail.paging.SimilarDataSource
import javax.inject.Inject
import javax.inject.Named

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetails,
    private val movieItemMapper: MovieItemMapper,
    private val getSimilarMovies: GetSimilarMovies,
    @Named("api_key") private val api_key: String,
    @Named("language") private val language: String,
    @Named("pageSize") private val pageSize: String
) : ViewModel() {

    val movieTitleLiveData by lazy { MutableLiveData<String>() }
    val moviePosterPathLiveData by lazy { MutableLiveData<String>() }
    val movieOverviewLiveData by lazy { MutableLiveData<String>() }
    val moviePopularityLiveData by lazy { MutableLiveData<Double>() }
    val movieVoteAverageLiveData by lazy { MutableLiveData<Double>() }
    val movieReleaseDateLiveData by lazy { MutableLiveData<String>() }
    val movieGenresLiveData by lazy { MutableLiveData<String>() }
    val similarMovies = MediatorLiveData<PagingData<MovieItem>>()

    private fun getMovieDetails(movie_id: Int, api_key: String, language: String) {
        val getMovieDetailsObserver = object : DefaultSingleObserver<MovieDetail>() {
            override fun onSuccess(t: MovieDetail) {
                val movieDetailItem = movieItemMapper.mapMovieDetailToPresentation(t)
                movieTitleLiveData.postValue(movieDetailItem.title)
                moviePosterPathLiveData.postValue(movieDetailItem.poster_path)
                movieOverviewLiveData.postValue(movieDetailItem.overview)
                moviePopularityLiveData.postValue(movieDetailItem.popularity)
                movieVoteAverageLiveData.postValue(movieDetailItem.vote_average)
                movieReleaseDateLiveData.postValue(movieDetailItem.release_date)
                movieGenresLiveData.postValue(movieDetailItem.genres.joinToString {
                    it.name
                })
            }
        }

        val params = GetMovieDetailsParams(movie_id, api_key, language)
        getMovieDetails.execute(getMovieDetailsObserver, params)
    }

    fun onMovieIdReceived(movie_id: Int) {
        getMovieDetails(
            movie_id,
            api_key,
            language
        )
        getSimilarMovies(movie_id)
    }

    fun onMovieItemClicked(movieItem: MovieItem) {
        onMovieIdReceived(movieItem.id)
    }

    private fun setDataSource(movieId: Int): LiveData<PagingData<MovieItem>> {
        val dataSource =
            SimilarDataSource(
                getSimilarMovies,
                movieId,
                api_key,
                language,
                movieItemMapper
            )
        return Pager(PagingConfig(pageSize = pageSize.toInt(), enablePlaceholders = true)) {
            dataSource
        }.liveData.cachedIn(viewModelScope)
    }

    private fun getSimilarMovies(movie_id: Int) {
        similarMovies.addSource(setDataSource(movie_id)) {
            similarMovies.postValue(it)
        }
    }

}