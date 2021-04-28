package com.example.data.entity

import com.example.domain.model.Genre

data class MovieDetailEntity(
    val title: String,
    val poster_path:String,
    val overview:String,
    val popularity: Double,
    val vote_average:Double,
    val release_date:String,
    val genres:List<Genre>
)