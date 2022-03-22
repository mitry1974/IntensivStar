package ru.androidschool.intensiv.data.repository.movies.nowPlaying

import io.reactivex.Observable
import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface

import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.data.repository.movies.common.MoviesRemoteDataSourceInterface

class NowPlayingMoviesRemoteDataSource(private val service: TMDBInterface) : BaseRemoteDataSource(),
    MoviesRemoteDataSourceInterface {
    override fun loadItemsList(): Observable<Result<MoviesResponse>> =
        getResult {
            service.loadNowPlaying()
        }
}