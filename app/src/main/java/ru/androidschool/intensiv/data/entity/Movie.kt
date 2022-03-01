package ru.androidschool.intensiv.data.entity

import ru.androidschool.intensiv.api.model.MovieResponse
import java.util.*

class Movie(
    val id: Int,
    val title: String? = "",
    val releaseDate: Date,
    val voteAverage: Double = 0.0,
    val studio: String,
    val genres: List<Int>,
    val actors: List<Actor>

) {
    constructor(movieResponse: MovieResponse) : this(
        id = movieResponse.id,
        title = movieResponse.title,
        releaseDate = movieResponse.releaseDate,
        voteAverage = movieResponse.voteAverage,
        studio = "",
        genres = movieResponse.genreIds,
        actors = listOf()
    )

    val rating: Float
        get() = voteAverage.div(2).toFloat()
}