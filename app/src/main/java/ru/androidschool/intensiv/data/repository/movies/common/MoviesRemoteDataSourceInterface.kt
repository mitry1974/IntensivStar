package ru.androidschool.intensiv.data.repository.movies.common

import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.MovieDetailsResponse
import ru.androidschool.intensiv.api.model.MoviesResponse

interface MoviesRemoteDataSourceInterface {
    suspend fun loadItemsList(): Result<MoviesResponse>
}