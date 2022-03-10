package ru.androidschool.intensiv.data.repository.movies.common

import io.reactivex.rxjava3.core.Observable
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.data.entity.Movie
import java.util.concurrent.Callable

open class BaseMoviesListRepository(private val remoteDataSource: MoviesRemoteDataSourceInterface) :
    ItemListRepositoryInterface<MoviesResponse, Movie> {
    open var itemsList: Observable<Map<Int, Movie>> = Observable.fromCallable { getItemsList() }


    override fun mapListResponse(response: MoviesResponse): Map<Int, Movie> =
        response.results?.let {
            it.fold(mutableMapOf()) { acc, value ->
                if (value.id != null) {
                    acc[value.id] = Movie(
                        id = value.id,
                        title = value.title ?: "",
                        voteAverage = value.voteAverage ?: 0F,
                        posterPath = value.posterPath ?: ""
                    )
                }
                acc
            }
        } ?: mapOf()



    override fun getItemsList(): Map<Int, Movie> {
        val result = remoteDataSource.loadItemsList()
        return if (result is Result.Success && result.successed) {
            mapListResponse(result.data)
        } else {
            throw Exception("Error loading now playing films")
        }
    }
}