package ru.androidschool.intensiv.data.repository.mappers

import ru.androidschool.intensiv.api.model.CreditsResponse
import ru.androidschool.intensiv.api.model.MovieDetailsResponse
import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.data.entity.Actor
import ru.androidschool.intensiv.data.entity.Movie
import ru.androidschool.intensiv.data.entity.MovieDetails
import ru.androidschool.intensiv.util.extensions.getYear

object ResponseMappers {
    fun creditsResponseToActorList(response: CreditsResponse): List<Actor> =
        response.cast?.map { Actor(it.name, it.profilePath ?: "") } ?: emptyList()

    fun moviesResponseToMoviesList(response: MoviesResponse): List<Movie> =
        response.results?.let {
            it.fold(mutableListOf()) { acc, value ->
                if (value.id != null) {
                    acc.add(
                        Movie(
                            id = value.id,
                            title = value.title ?: "",
                            voteAverage = value.voteAverage ?: 0F,
                            posterPath = value.posterPath ?: ""
                        )
                    )
                }
                acc
            }
        } ?: emptyList()

    fun movieDetailsResponseToMovieDetails(response: MovieDetailsResponse): MovieDetails =
        MovieDetails(
            id = response.id ?: 0,
            title = response.title.orEmpty(),
            voteAverage = response.voteAverage ?: 0F,
            productionCompanies = response.productionCompanies?.joinToString(", ").orEmpty(),
            genres = response.genres?.joinToString(", ").orEmpty(),
            overview = response.overview ?: "",
            year = response.releaseDate?.getYear().orEmpty(),
            posterPath = response.posterPath.orEmpty()
        )
}