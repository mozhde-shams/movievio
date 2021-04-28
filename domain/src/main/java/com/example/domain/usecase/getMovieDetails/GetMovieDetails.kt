package com.example.domain.usecase.getMovieDetails

import com.example.domain.model.MovieDetail
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.base.UseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class GetMovieDetails(
    executorThread: Scheduler, uiThread: Scheduler,
    private val movieRepository: MovieRepository
) : UseCase<MovieDetail, GetMovieDetailsParams>(executorThread, uiThread) {

    override fun buildUseCaseObservable(params: GetMovieDetailsParams): Single<MovieDetail> {
        return movieRepository.getMoviePrimaryInfo(params.movie_id, params.api_key, params.language)
    }

}