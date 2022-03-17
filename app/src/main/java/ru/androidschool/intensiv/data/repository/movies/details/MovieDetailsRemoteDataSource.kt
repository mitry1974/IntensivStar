package ru.androidschool.intensiv.data.repository.movies.details

import io.reactivex.Observable
import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.responses.CreditsResponse
import ru.androidschool.intensiv.api.responses.MovieDetailsResponse

class MovieDetailsRemoteDataSource(private val service: TMDBInterface) : BaseRemoteDataSource() {
    fun loadItemDetails(id: Int): Observable<Result<MovieDetailsResponse>> =
        getResult {
            service.loadMovieDetails(id)
        }

    fun loadCredits(movieId: Int): Observable<Result<CreditsResponse>> =
        getResult {
            service.loadMovieCredits(movieId)
        }
}
