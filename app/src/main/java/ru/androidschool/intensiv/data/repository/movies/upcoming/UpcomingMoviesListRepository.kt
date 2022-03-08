package ru.androidschool.intensiv.data.repository.movies.upcoming

import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.data.repository.movies.common.BaseMoviesListRepository

class UpcomingMoviesListRepository() :
    BaseMoviesListRepository(UpcomingMoviesRemoteDataSource(TMDBInterface.apiClient))