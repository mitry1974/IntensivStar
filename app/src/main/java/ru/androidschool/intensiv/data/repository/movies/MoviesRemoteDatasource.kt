package ru.androidschool.intensiv.data.repository.movies

import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.*

class MoviesRemoteDatasource(private val service: TMDBInterface) : BaseRemoteDataSource() {

    suspend fun loadGenres(): Result<GenresResponse> =
        getResult {
            service.loadGenres()
        }





    suspend fun loadTvShows(): Result<TvShowsListResponse> =
        getResult {
            service.loadTvShows()
        }
}