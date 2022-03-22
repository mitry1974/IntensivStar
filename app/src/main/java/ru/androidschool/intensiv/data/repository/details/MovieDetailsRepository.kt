package ru.androidschool.intensiv.data.repository.details

import io.reactivex.Observable
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.repository.credits.CreditsRemoteRepository
import ru.androidschool.intensiv.domain.models.MovieDetails

class MovieDetailsRepository {
    private val remoteDetailsDataSource = MovieDetailsRemoteRepository(TMDBInterface.apiClient)
    private val remoteActorsDataSource = CreditsRemoteRepository(TMDBInterface.apiClient)

    fun getMovieDetails(movieId: Int): Observable<MovieDetails> =
        remoteDetailsDataSource.loadItemDetails(movieId)

    fun getActors(movieId: Int) =
        remoteActorsDataSource.loadCredits(movieId)
}
