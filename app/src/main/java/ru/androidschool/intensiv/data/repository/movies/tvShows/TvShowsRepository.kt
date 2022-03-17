package ru.androidschool.intensiv.data.repository.movies.tvShows

import io.reactivex.Observable
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.models.TvShow
import ru.androidschool.intensiv.data.repository.mappers.ResponseTvShowMapper

class TvShowsRepository() {
    private val remoteDataSource = TvShowsRemoteDataSource(TMDBInterface.apiClient)

    fun getTvShows(): Observable<List<TvShow>> =
        remoteDataSource.loadItemsList().map { result ->
            if (result is Result.Success && result.successed) {
                result.data.results?.filter { it.id != 0 }?.map { ResponseTvShowMapper.toVO(it) }
            } else {
                throw Exception("Error loading tv shows list")
            }
        }
}