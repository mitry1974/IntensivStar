package ru.androidschool.intensiv.data.repository.movies.popular

import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.data.repository.movies.common.BaseMoviesListRepository

class PopularMoviesListRepository :
    BaseMoviesListRepository(PopularMoviesRemoteDataSource(TMDBInterface.apiClient))