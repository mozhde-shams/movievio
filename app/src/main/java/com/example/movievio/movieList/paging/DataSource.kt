package com.example.movievio.movieList.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.domain.usecase.getMovies.GetMovieListParams
import com.example.domain.usecase.getMovies.GetMovies
import com.example.domain.usecase.getMovies.MovieType
import com.example.movievio.mapper.MovieItemMapper
import com.example.movievio.model.MovieItem
import io.reactivex.Single

class DataSource(
    private val movieItemMapper: MovieItemMapper,
    private val getMovies: GetMovies,
    private val api_key: String,
    private val language: String,
    private val movieType: MovieType
) : RxPagingSource<Int, MovieItem>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MovieItem>> {
        val currentPageKey = params.key ?: 1

        return getMovies.setScheduler(
            GetMovieListParams(
                api_key,
                language,
                currentPageKey,
                movieType
            )
        )
            .map<LoadResult<Int, MovieItem>> {
                val totalPages = it.total_pages
                LoadResult.Page(
                    data = it.results.map { movieProperty ->
                        movieItemMapper.mapMoviePropertyItemToPresentation(
                            movieProperty
                        )
                    },
                    prevKey = null,
                    nextKey = if (currentPageKey == totalPages) null
                    else currentPageKey.plus(1)

                )
            }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
