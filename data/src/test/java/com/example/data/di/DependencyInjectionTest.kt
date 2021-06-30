package com.example.data.di

import com.example.data.repository.MovieRepositoryImpl
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DependencyInjectionTest {

    @Test
    fun getMovieRepositoryImpl(){
        val movieRepositoryImpl = DaggerDataComponent.create().getMovieRepositoryImpl()
        Assert.assertThat(
            movieRepositoryImpl,
            CoreMatchers.instanceOf(MovieRepositoryImpl::class.java)
        )
    }

}