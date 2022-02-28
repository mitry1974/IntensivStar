package ru.androidschool.intensiv.data

class Movie(
    val title: String? = "",
    val year: Int,
    val voteAverage: Double = 0.0,
    val studio: String,
    val genres: List<String>,
    val actors: List<Actor>

) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
