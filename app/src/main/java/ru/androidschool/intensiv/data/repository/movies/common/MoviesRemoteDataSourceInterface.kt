package ru.androidschool.intensiv.data.repository.movies.common

import io.reactivex.Observable
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.MoviesResponse

interface MoviesRemoteDataSourceInterface {
    fun loadItemsList(): Observable<Result<MoviesResponse>>
}