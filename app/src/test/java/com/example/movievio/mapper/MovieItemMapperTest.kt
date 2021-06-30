package com.example.movievio.mapper

import com.example.domain.model.MovieDetail
import com.example.domain.model.MovieProperty
import com.example.movievio.AppTestData
import org.junit.Assert
import org.junit.Test

class MovieItemMapperTest {

    @Test
    fun mapMoviePropertyItemToPresentation() {
        val movieProperty = MovieProperty(
            AppTestData.id,
            AppTestData.poster_path,
            AppTestData.title
        )

        val moviePropertyItem = MovieItemMapper().mapMoviePropertyItemToPresentation(movieProperty)
        Assert.assertEquals(movieProperty.id, moviePropertyItem.id)
        Assert.assertEquals(
            AppTestData.IMAGE_BASE_URL.plus(movieProperty.poster_path),
            moviePropertyItem.poster_path
        )
        Assert.assertEquals(movieProperty.title, moviePropertyItem.title)
    }

    @Test
    fun mapMovieDetailToPresentation() {

        val movieDetails = MovieDetail(
            AppTestData.title,
            AppTestData.poster_path,
            AppTestData.overview,
            AppTestData.popularity,
            AppTestData.vote_average,
            AppTestData.release_date,
            AppTestData.genres
        )

        val movieDetailItem = MovieItemMapper().mapMovieDetailToPresentation(movieDetails)

        Assert.assertEquals(movieDetails.title, movieDetailItem.title)
        Assert.assertEquals(
            AppTestData.IMAGE_BASE_URL.plus(movieDetails.poster_path),
            movieDetailItem.poster_path
        )
        Assert.assertEquals(movieDetails.overview, movieDetailItem.overview)
        Assert.assertEquals(movieDetails.popularity, movieDetailItem.popularity, 0.0)
        Assert.assertEquals(movieDetails.vote_average, movieDetailItem.vote_average, 0.0)
        Assert.assertEquals(movieDetails.release_date, movieDetailItem.release_date)
        Assert.assertEquals(movieDetails.genres, movieDetailItem.genres)

    }
}