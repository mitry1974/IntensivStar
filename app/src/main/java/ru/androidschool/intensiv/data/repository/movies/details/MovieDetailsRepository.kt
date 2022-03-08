package ru.androidschool.intensiv.data.repository.movies.details

import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.model.CreditsResponse
import ru.androidschool.intensiv.api.model.MovieDetailsResponse
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.data.entity.Actor
import ru.androidschool.intensiv.data.entity.MovieDetails
import ru.androidschool.intensiv.util.extensions.getYear

class MovieDetailsRepository() {
    private val remoteDataSource = MovieDetailsRemoteDataSource(TMDBInterface.apiClient)

    private fun mapMovieResponse(response: MovieDetailsResponse): MovieDetails =
        MovieDetails(
            id = response.id ?: 0,
            title = response.title ?: "",
            voteAverage = response.voteAverage ?: 0F,
            productionCompanies = response.productionCompanies?.joinToString(", ") ?: "",
            genres = response.genres?.joinToString(", ") ?: "",
            overview = response.overview ?: "",
            year = response.releaseDate?.getYear() ?: "",
            posterPath = response.posterPath ?: ""
        )

    private fun mapCreditsResponse(response: CreditsResponse): List<Actor> =
        response.cast?.map { Actor(it.name, it.profilePath ?: "") } ?: listOf()

    suspend fun getItemDetails(itemId: Int): MovieDetails {
        val result = remoteDataSource.loadItemDetails(itemId)
        return if (result is Result.Success && result.successed) {
            mapMovieResponse(result.data)

        } else {
            throw Exception("Error loading movie details")
        }
    }

    suspend fun getCredits(itemId: Int): List<Actor> {
        val result = remoteDataSource.loadCredits(itemId)
        return if (result is Result.Success && result.successed) {
            mapCreditsResponse(result.data)
        } else {
            throw Exception("Error loading movie credits")
        }
    }
}