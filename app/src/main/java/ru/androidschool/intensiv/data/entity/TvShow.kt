package ru.androidschool.intensiv.data.entity

import ru.androidschool.intensiv.api.model.TvShowResponse

data class TvShow(
    val id: Int,
    val name: String,
    val voteAverage: Float,
    val posterPath: String
)
