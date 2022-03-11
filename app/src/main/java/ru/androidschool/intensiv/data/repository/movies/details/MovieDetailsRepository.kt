package ru.androidschool.intensiv.data.repository.movies.details

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.model.CreditsResponse
import ru.androidschool.intensiv.api.model.MovieDetailsResponse
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.data.entity.Actor
import ru.androidschool.intensiv.data.entity.MovieDetails
import ru.androidschool.intensiv.data.repository.mappers.ResponseMappers
import ru.androidschool.intensiv.util.extensions.getYear

class MovieDetailsRepository() {
    private val remoteDataSource = MovieDetailsRemoteDataSource(TMDBInterface.apiClient)

    fun getItemDetails(itemId: Int): Observable<MovieDetails> =
        remoteDataSource.loadItemDetails(itemId)
            .map { result ->
                if (result is Result.Success && result.successed) {
                    ResponseMappers.movieDetailsResponseToMovieDetails(result.data)

                } else {
                    throw Exception("Error loading movie details")
                }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getCredits(itemId: Int): Observable<List<Actor>> =
        remoteDataSource.loadCredits(itemId).map { result ->
            if (result is Result.Success && result.successed) {
                ResponseMappers.creditsResponseToActorList(result.data)
            } else {
                throw Exception("Error loading movie credits")
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}