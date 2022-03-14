package ru.androidschool.intensiv.data.repository.movies.details

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.data.entity.Actor
import ru.androidschool.intensiv.data.entity.MovieDetails
import ru.androidschool.intensiv.data.repository.mappers.ActorMapper
import ru.androidschool.intensiv.data.repository.mappers.MovieDetailsMapper

class MovieDetailsRepository() {
    private val remoteDataSource = MovieDetailsRemoteDataSource(TMDBInterface.apiClient)

    fun getItemDetails(itemId: Int): Observable<MovieDetails> =
        remoteDataSource.loadItemDetails(itemId)
            .map { result ->
                if (result is Result.Success && result.successed) {
                    MovieDetailsMapper.toVO(result.data)
                } else {
                    throw Exception("Error loading movie details")
                }
            }

    fun getCredits(itemId: Int): Observable<List<Actor>> =
        remoteDataSource.loadCredits(itemId).map { result ->
            if (result is Result.Success && result.successed) {
                ActorMapper.toVO(result.data.cast ?: emptyList())
            } else {
                throw Exception("Error loading movie credits")
            }
        }
}