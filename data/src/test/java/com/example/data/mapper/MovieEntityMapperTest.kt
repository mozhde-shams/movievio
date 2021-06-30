package com.example.data.mapper

import com.example.data.DataTestData
import com.example.data.entity.MovieDetailEntity
import com.example.data.entity.MovieListEntity
import com.example.data.entity.MoviePropertyEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieEntityMapperTest {

    private lateinit var movieEntityMapper: MovieEntityMapper

    @Before
    fun setUp() {
        movieEntityMapper = MovieEntityMapper()
    }

    @Test
    fun mapMovieListEntityToDomain() {
        val movieListEntity = MovieListEntity(
            DataTestData.resultsOfEntity,
            DataTestData.page,
            DataTestData.total_pages
        )

        val movieList = movieEntityMapper.mapMovieListEntityToDomain(movieListEntity)

        for (cnt in (movieListEntity.results.indices)) {
            Assert.assertEquals(movieListEntity.results[cnt].id, movieList.results[cnt].id)
            Assert.assertEquals(
                movieListEntity.results[cnt].poster_path,
                movieList.results[cnt].poster_path
            )
            Assert.assertEquals(movieListEntity.results[cnt].title, movieList.results[cnt].title)
        }

        Assert.assertEquals(movieListEntity.page, movieList.page)
        Assert.assertEquals(movieListEntity.total_pages, movieList.total_pages)
    }

    @Test
    fun mapMoviePropertyEntityToDomain() {
        val moviePropertyEntity = MoviePropertyEntity(
            DataTestData.id,
            DataTestData.poster_path,
            DataTestData.title
        )

        val movieProperty = movieEntityMapper.mapMoviePropertyEntityToDomain(moviePropertyEntity)

        Assert.assertEquals(moviePropertyEntity.id, movieProperty.id)
        Assert.assertEquals(moviePropertyEntity.poster_path, movieProperty.poster_path)
        Assert.assertEquals(moviePropertyEntity.title, movieProperty.title)
    }

    @Test
    @Ignore
    fun mapMovieDetailsEntityToDomain() {
        val movieDetailEntity = MovieDetailEntity(
            DataTestData.title,
            DataTestData.poster_path,
            DataTestData.overview,
            DataTestData.popularity,
            DataTestData.vote_average,
            DataTestData.release_date,
            DataTestData.genres
        )

        val movieDetail = movieEntityMapper.mapMovieDetailsEntityToDomain(movieDetailEntity)

        Assert.assertEquals(movieDetailEntity.title, movieDetail.title)
        Assert.assertEquals(movieDetailEntity.poster_path, movieDetail.poster_path)
        Assert.assertEquals(movieDetailEntity.overview, movieDetail.overview)
        Assert.assertEquals(movieDetailEntity.popularity, movieDetail.popularity, 0.0)
        Assert.assertEquals(movieDetailEntity.vote_average, movieDetail.vote_average, 0.0)
        Assert.assertEquals(movieDetailEntity.release_date, movieDetail.release_date)
        Assert.assertEquals(movieDetailEntity.genres, movieDetail.genres)
        for (cnt in (movieDetailEntity.genres.indices)) {
            Assert.assertEquals(movieDetailEntity.genres[cnt].name, movieDetail.genres[cnt].name)
        }
    }

}