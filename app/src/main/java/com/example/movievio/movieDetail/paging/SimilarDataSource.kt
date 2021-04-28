package com.example.movievio.movieDetail.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.domain.usecase.getSimilarMovies.GetSimilarMovies
import com.example.domain.usecase.getSimilarMovies.GetSimilarMoviesParams
import com.example.movievio.mapper.MovieItemMapper
import com.example.movievio.model.MovieItem
import io.reactivex.Single

class SimilarDataSource(
    private val getSimilarMovies: GetSimilarMovies,
    private val movie_id: Int,
    private val api_key: String,
    private val language: String,
    private val movieItemMapper: MovieItemMapper
) : RxPagingSource<Int, MovieItem>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MovieItem>> {
        val currentPageKey = params.key ?: 1

        return getSimilarMovies.setScheduler(
            GetSimilarMoviesParams(
                movie_id, api_key, language, currentPageKey
            )
        ).map<LoadResult<Int, MovieItem>> {
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