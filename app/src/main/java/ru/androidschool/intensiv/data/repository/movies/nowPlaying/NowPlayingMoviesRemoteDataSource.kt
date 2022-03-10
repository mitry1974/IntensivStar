package ru.androidschool.intensiv.data.repository.movies.nowPlaying

import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface

import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.data.repository.movies.common.MoviesRemoteDataSourceInterface

class NowPlayingMoviesRemoteDataSource(private val service: TMDBInterface) : BaseRemoteDataSource(),
    MoviesRemoteDataSourceInterface {
    override fun loadItemsList(): Result<MoviesResponse> =
        getResult {
            service.loadNowPlaying()
        }
}