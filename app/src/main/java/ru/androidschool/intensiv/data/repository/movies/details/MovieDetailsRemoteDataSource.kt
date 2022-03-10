package ru.androidschool.intensiv.data.repository.movies.details

import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.model.CreditsResponse
import ru.androidschool.intensiv.api.model.MovieDetailsResponse

class MovieDetailsRemoteDataSource(private val service: TMDBInterface) : BaseRemoteDataSource() {
    fun loadItemDetails(id: Int): Result<MovieDetailsResponse> =
        getResult {
            service.loadMovieDetails(id)
        }

    fun loadCredits(movieId: Int): Result<CreditsResponse> =
        getResult {
            service.loadMovieCredits(movieId)
        }
}
