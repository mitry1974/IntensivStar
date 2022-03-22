package ru.androidschool.intensiv.data.repository.tvShows

import io.reactivex.Observable
import ru.androidschool.intensiv.data.api.BaseRemoteRepository
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.api.responses.TvShowsListResponse
import ru.androidschool.intensiv.data.mappers.ResponseToTvShowMapper
import ru.androidschool.intensiv.domain.models.TvShow

class TvShowsRemoteRepository(private val service: TMDBInterface) :
    BaseRemoteRepository<TvShowsListResponse>() {
    fun loadItemsList(): Observable<List<TvShow>> =
        getResult {
            service.loadTvShows()
        }.map {
            it.results?.filter { response -> response.id != 0 }
                ?.map { mr -> ResponseToTvShowMapper.toVO(mr) }
        }
}
