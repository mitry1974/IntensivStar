package ru.androidschool.intensiv.models

class Movie(
    val id: Int,
    val title: String = "",
    val voteAverage: Float,
    val posterPath: String,
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat() ?: 0F
}