package com.example.movievio.di

import com.example.data.di.DaggerDataComponent
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun movieRepository(): MovieRepository =
        DaggerDataComponent.create().getMovieRepositoryImpl()
}