package ru.androidschool.intensiv.data.repository.movies.nowPlaying
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.data.repository.movies.common.BaseMoviesListRepository

class NowPlayingMoviesListRepository() :
    BaseMoviesListRepository(NowPlayingMoviesRemoteDataSource(TMDBInterface.apiClient))