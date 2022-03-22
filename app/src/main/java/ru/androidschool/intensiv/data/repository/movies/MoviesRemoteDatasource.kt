package ru.androidschool.intensiv.data.repository.movies

import io.reactivex.Observable
import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.*

class MoviesRemoteDatasource(private val service: TMDBInterface) : BaseRemoteDataSource() {

    suspend fun loadGenres(): Observable<Result<GenresResponse>> =
        getResult {
            service.loadGenres()
        }





    suspend fun loadTvShows(): Observable<Result<TvShowsListResponse>> =
        getResult {
            service.loadTvShows()
        }
}