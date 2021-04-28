package com.example.movievio.movieList

import androidx.lifecycle.*
import androidx.paging.*
import com.example.domain.usecase.getMovies.GetMovies
import com.example.domain.usecase.getMovies.MovieType
import com.example.movievio.mapper.MovieItemMapper
import com.example.movievio.model.MovieItem
import com.example.movievio.movieList.paging.DataSource
import com.example.movievio.utils.Event
import javax.inject.Inject
import javax.inject.Named

class MovieListViewModel @Inject constructor(
    private val movieItemMapper: MovieItemMapper,
    private val getMovies: GetMovies,
    @Named("api_key") private val apiKey: String,
    @Named("language") private val language: String,
    @Named("pageSize") private val pageSize: String
) : ViewModel() {

    val navigateToDetails by lazy { MutableLiveData<Event<Int>>() }
    val movies = MediatorLiveData<PagingData<MovieItem>>()

    init {
        getPopularMovies()
    }

    private fun setDataSource(movieType: MovieType): LiveData<PagingData<MovieItem>> {
        val dataSource = DataSource(movieItemMapper, getMovies, apiKey, language, movieType)
        return Pager(PagingConfig(pageSize = pageSize.toInt(), enablePlaceholders = true)) {
            dataSource
        }.liveData.cachedIn(viewModelScope)
    }

    fun getPopularMovies() {
        val popularMoviesList = setDataSource(MovieType.POPULAR)
        movies.addSource(popularMoviesList) {
            movies.postValue(it)
        }
    }

    fun getTopRatedMovies() {
        val topRatedMoviesList = setDataSource(MovieType.TOPRATED)
        movies.addSource(topRatedMoviesList) {
            movies.postValue(it)
        }
    }

    fun getNowPlayingMovies() {
        val nowPlayingMoviesList = setDataSource(MovieType.NOWPLAYING)
        movies.addSource(nowPlayingMoviesList) {
            movies.postValue(it)
        }
    }


    fun getUpcomingMovies() {
        val upcomingMoviesList = setDataSource(MovieType.UPCOMING)
        movies.addSource(upcomingMoviesList) {
            movies.postValue(it)
        }
    }

    fun onMovieItemClicked(movieItem:MovieItem) {
        navigateToDetails.postValue(Event(movieItem.id))
    }

}