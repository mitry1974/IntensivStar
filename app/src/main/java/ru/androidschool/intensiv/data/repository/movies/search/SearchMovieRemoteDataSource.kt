package ru.androidschool.intensiv.data.repository.movies.search

import io.reactivex.Observable
import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.TMDBInterface
import ru.androidschool.intensiv.api.responses.MoviesResponse

class SearchMovieRemoteDataSource(private val service: TMDBInterface) : BaseRemoteDataSource() {
    fun searchMovies(query: String): Observable<Result<MoviesResponse>> =
        getResult {
            service.searchMovie(query)
        }
}