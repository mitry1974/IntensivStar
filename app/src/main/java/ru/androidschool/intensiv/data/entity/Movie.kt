package ru.androidschool.intensiv.data.entity

import ru.androidschool.intensiv.api.model.MovieResponse
import java.util.*

class Movie(
    val id: Int,
    val title: String = "",
    val voteAverage: Float,

    val posterPath: String,

    ) {

    val rating: Float
        get() = voteAverage.div(2).toFloat() ?: 0F
}