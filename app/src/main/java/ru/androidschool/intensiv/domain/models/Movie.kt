package ru.androidschool.intensiv.domain.models

class Movie(
    val id: Int,
    val title: String = "",
    val voteAverage: Float,
    val posterPath: String
) {
    val rating: Float
        get() = voteAverage.div(2)
}
