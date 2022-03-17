package ru.androidschool.intensiv.data.repository.movies.popular

import io.reactivex.Observable
import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.responses.MoviesResponse
import ru.androidschool.intensiv.data.repository.movies.common.MoviesRemoteDataSourceInterface


class PopularMoviesRemoteDataSource(private val service: TMDBInterface) : BaseRemoteDataSource(),
    MoviesRemoteDataSourceInterface {
    override fun loadItemsList(): Observable<Result<MoviesResponse>> =
        getResult {
            service.loadPopular()
        }
}