package ru.androidschool.intensiv.data.repository.details

import io.reactivex.Observable
import ru.androidschool.intensiv.data.repository.credits.CreditsRemoteRepository
import ru.androidschool.intensiv.domain.models.MovieDetails
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val remoteDetailsDataSource: MovieDetailsRemoteDataSource,
    private val remoteActorsDataSource: CreditsRemoteRepository
) {


    fun getMovieDetails(movieId: Int): Observable<MovieDetails> =
        remoteDetailsDataSource.loadItemDetails(movieId)

    fun getActors(movieId: Int) =
        remoteActorsDataSource.loadCredits(movieId)
}
