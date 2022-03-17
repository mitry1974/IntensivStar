package ru.androidschool.intensiv.data.repository.movies.common

import io.reactivex.Observable
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.responses.MoviesResponse
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.models.Movie
import ru.androidschool.intensiv.data.repository.mappers.ResponseToMovieMapper

open class BaseMoviesListRepository(private val remoteDataSource: MoviesRemoteDataSourceInterface) :
    ItemListRepositoryInterface<MoviesResponse, Movie> {
    override fun getItemsList(): Observable<List<Movie>> =
        remoteDataSource.loadItemsList()
            .map { result ->
                if (result is Result.Success && result.successed) {
                    result.data.results?.filter { it.id != 0 }?.map { ResponseToMovieMapper.toVO(it) }
                } else {
                    throw Exception("Error loading now playing films")
                }
            }
}

