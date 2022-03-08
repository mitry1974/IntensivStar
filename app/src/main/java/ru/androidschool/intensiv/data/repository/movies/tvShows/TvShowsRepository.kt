package ru.androidschool.intensiv.data.repository.movies.tvShows

import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.data.entity.TvShow

class TvShowsRepository() {
    private val remoteDataSource = TvShowsRemoteDataSource(TMDBInterface.apiClient)

    fun getTvShows(): List<TvShow> =
        listOf<TvShow>()



}