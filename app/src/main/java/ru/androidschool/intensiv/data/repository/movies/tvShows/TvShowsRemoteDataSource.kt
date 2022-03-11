package ru.androidschool.intensiv.data.repository.movies.tvShows

import io.reactivex.Observable
import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.model.TvShowsListResponse

class TvShowsRemoteDataSource(private val service: TMDBInterface) :
    BaseRemoteDataSource() {
    suspend fun loadItemsList(): Observable<Result<TvShowsListResponse>> =
        getResult {
            service.loadTvShows()
        }
}