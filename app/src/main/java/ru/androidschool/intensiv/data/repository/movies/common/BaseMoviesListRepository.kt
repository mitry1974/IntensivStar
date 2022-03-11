package ru.androidschool.intensiv.data.repository.movies.common

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.data.entity.Movie
import ru.androidschool.intensiv.data.repository.mappers.ResponseMappers

open class BaseMoviesListRepository(private val remoteDataSource: MoviesRemoteDataSourceInterface) :
    ItemListRepositoryInterface<MoviesResponse, Movie> {
    override fun getItemsList(): Observable<List<Movie>> =
        remoteDataSource.loadItemsList()
            .map { result ->
                if (result is Result.Success && result.successed) {
                    ResponseMappers.moviesResponseToMoviesList(result.data)
                } else {
                    throw Exception("Error loading now playing films")
                }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
