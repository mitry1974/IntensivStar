package ru.androidschool.intensiv.data.repository.movies.upcoming

import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.data.repository.movies.common.MoviesRemoteDataSourceInterface

class UpcomingMoviesRemoteDataSource(private val service: TMDBInterface) : BaseRemoteDataSource(),
    MoviesRemoteDataSourceInterface {
    override suspend fun loadItemsList(): Result<MoviesResponse> =
        getResult {
            service.loadUpcoming()
        }
}