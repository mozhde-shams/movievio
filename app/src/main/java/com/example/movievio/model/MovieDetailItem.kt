package com.example.movievio.model

import com.example.domain.model.Genre

data class MovieDetailItem(
    val title: String,
    val poster_path:String,
    val overview:String,
    val popularity: Double,
    val vote_average:Double,
    val release_date:String,
    val genres:List<Genre>
)