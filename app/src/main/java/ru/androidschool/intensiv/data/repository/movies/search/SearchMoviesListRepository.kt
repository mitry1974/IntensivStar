package ru.androidschool.intensiv.data.repository.movies.search

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.data.entity.Movie
import ru.androidschool.intensiv.data.repository.mappers.ResponseMappers

class SearchMoviesListRepository {
    private val remoteDataSource = SearchMovieRemoteDataSource(TMDBInterface.apiClient)

    fun searchMovies(query: String): Observable<List<Movie>> =
        remoteDataSource.searchMovies(query).map { result ->
            if (result is Result.Success && result.successed) {
                ResponseMappers.moviesResponseToMoviesList(result.data)
            } else {
                throw Exception("Error loading now playing films")
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
