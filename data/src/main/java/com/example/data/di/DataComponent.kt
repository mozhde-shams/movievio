package com.example.data.di

import com.example.data.repository.MovieRepositoryImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoriesModule::class])
interface DataComponent {
    fun getMovieRepositoryImpl(): MovieRepositoryImpl
}