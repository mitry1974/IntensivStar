package ru.androidschool.intensiv.data.entity

import ru.androidschool.intensiv.api.model.MovieDetailsResponse
import ru.androidschool.intensiv.util.extensions.getYear
import java.util.*

class MovieDetails(
    val id: Int,
    val title: String,
    val voteAverage: Float,
    val productionCompanies: String,
    val genre: String,
    val overview: String,
    val year: String,
    val posterPath: String
) {
    constructor(movieDetailsResponse: MovieDetailsResponse) : this(
        id = movieDetailsResponse.id,
        title = movieDetailsResponse.title,
        voteAverage = movieDetailsResponse.voteAverage,
        productionCompanies = movieDetailsResponse.productionCompanies.joinToString(", ") { it.name },
        genre = movieDetailsResponse.genres.joinToString(", ") { it.name },
        overview = movieDetailsResponse.overview,
        year = movieDetailsResponse.releaseDate.getYear(),
        posterPath = movieDetailsResponse.posterPath
    )
}
