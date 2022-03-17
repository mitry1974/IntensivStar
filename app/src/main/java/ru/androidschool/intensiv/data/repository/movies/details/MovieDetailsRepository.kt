package ru.androidschool.intensiv.data.repository.movies.details

import io.reactivex.Observable
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.models.Actor
import ru.androidschool.intensiv.models.MovieDetails
import ru.androidschool.intensiv.data.repository.mappers.ResponseToActorMapper
import ru.androidschool.intensiv.data.repository.mappers.RersponseToMovieDetailsMapper

class MovieDetailsRepository() {
    private val remoteDataSource = MovieDetailsRemoteDataSource(TMDBInterface.apiClient)

    fun getItemDetails(itemId: Int): Observable<MovieDetails> =
        remoteDataSource.loadItemDetails(itemId)
            .map { result ->
                if (result is Result.Success && result.successed) {
                    RersponseToMovieDetailsMapper.toVO(result.data)
                } else {
                    throw Exception("Error loading movie details")
                }
            }

    fun getCredits(itemId: Int): Observable<List<Actor>> =
        remoteDataSource.loadCredits(itemId).map { result ->
            if (result is Result.Success && result.successed) {
                ResponseToActorMapper.toVO(result.data.cast ?: emptyList())
            } else {
                throw Exception("Error loading movie credits")
            }
        }
}