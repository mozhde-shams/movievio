package com.example.domain.model

data class MovieDetail(
    val title: String,
    val poster_path:String,
    val overview:String,
    val popularity: Double,
    val vote_average:Double,
    val release_date:String,
    val genres:List<Genre>
)