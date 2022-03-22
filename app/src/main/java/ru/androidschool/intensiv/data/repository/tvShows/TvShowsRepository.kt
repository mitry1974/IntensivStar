package ru.androidschool.intensiv.data.repository.tvShows

import io.reactivex.Observable
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.domain.models.TvShow

class TvShowsRepository(
    private val remoteDataSource: TvShowsRemoteRepository
) {
    fun getTvShows(): Observable<List<TvShow>> =
        remoteDataSource.loadItemsList()
}
