package com.example.domain.usecase.getSimilarMovies

import com.example.domain.model.MovieList
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.base.UseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class GetSimilarMovies(
    executorThread: Scheduler,
    uiThread: Scheduler,
    private val movieRepository: MovieRepository
) : UseCase<MovieList, GetSimilarMoviesParams>(executorThread, uiThread) {

    override fun buildUseCaseObservable(params: GetSimilarMoviesParams): Single<MovieList> {
        return movieRepository.getSimilarMovies(
            params.movie_id,
            params.api_key,
            params.language,
            params.page
        )
    }

}