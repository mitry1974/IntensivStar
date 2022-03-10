package ru.androidschool.intensiv.data.repository.movies.common

import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.data.entity.Movie

open class BaseMoviesListRepository(private val remoteDataSource: MoviesRemoteDataSourceInterface) :
    ItemListRepositoryInterface<MoviesResponse, Movie> {
    open lateinit var itemsList: Map<Int, Movie>


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



    override suspend fun getItemsList() {
        val result = remoteDataSource.loadItemsList()
        return if (result is Result.Success && result.successed) {
            itemsList = mapListResponse(result.data)
        } else {
            throw Exception("Error loading now playing films")
        }
    }
}