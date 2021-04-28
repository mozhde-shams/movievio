package com.example.movievio.di

import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.getMovieDetails.GetMovieDetails
import com.example.domain.usecase.getMovies.GetMovies
import com.example.domain.usecase.getSimilarMovies.GetSimilarMovies
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class DomainModule {

    @Provides
    fun getPopularMovies(
        movieRepository: MovieRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): GetMovies {
        return GetMovies(
            ioScheduler,
            mainThreadScheduler,
            movieRepository
        )
    }

    @Provides
    fun getMovieDetails(
        movieRepository: MovieRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): GetMovieDetails {
        return GetMovieDetails(ioScheduler, mainThreadScheduler, movieRepository)
    }

    @Provides
    fun getSimilarMovies(
        movieRepository: MovieRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): GetSimilarMovies {
        return GetSimilarMovies(ioScheduler, mainThreadScheduler, movieRepository)
    }

    @Provides
    @Named("ioScheduler")
    fun ioScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named("mainThreadScheduler")
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}