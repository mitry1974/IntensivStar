package ru.androidschool.intensiv.data.repository.movies.tvShows

import io.reactivex.Observable
import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.responses.TvShowsListResponse

class TvShowsRemoteDataSource(private val service: TMDBInterface) :
    BaseRemoteDataSource() {
    fun loadItemsList(): Observable<Result<TvShowsListResponse>> =
        getResult {
            service.loadTvShows()
        }
}