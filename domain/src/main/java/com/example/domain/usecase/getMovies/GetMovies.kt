package com.example.domain.usecase.getMovies

import com.example.domain.model.MovieList
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.base.UseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class GetMovies(
    executorThread: Scheduler,
    uiThread: Scheduler,
    private val movieRepository: MovieRepository
) : UseCase<MovieList, GetMovieListParams>(executorThread, uiThread) {

    override fun buildUseCaseObservable(params: GetMovieListParams): Single<MovieList> {
        return movieRepository.getMoviesList(
            params.movieType.type,
            params.api_key,
            params.language,
            params.page
        )
    }

}