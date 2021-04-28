package com.example.movievio.di

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    @Named("api_key")
    fun apiKey(): String = "***REMOVED***"

    @Singleton
    @Provides
    @Named("language")
    fun language(): String = "en-US"

    @Singleton
    @Provides
    @Named("pageSize")
    fun pageSize(): String = "20"

    /*@Singleton
    @Provides
    fun providePagingData(
        dataSource: DataSource,
        @Named("pageSize") pageSize: Int
    ): LiveData<PagingData<MovieItem>> {
        return Pager(PagingConfig(pageSize = pageSize, enablePlaceholders = true)) {
            dataSource
        }.liveData
    }*/

}