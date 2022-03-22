package ru.androidschool.intensiv.data.repository.movies.search

import io.reactivex.Observable
import ru.androidschool.intensiv.data.api.BaseRemoteRepository
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.api.responses.MoviesResponse
import ru.androidschool.intensiv.data.mappers.ResponseToMovieMapper
import ru.androidschool.intensiv.data.repository.movies.BaseMoviesRepository
import ru.androidschool.intensiv.domain.models.Movie

class SearchMovieRemoteRepository(private val service: TMDBInterface) :
    BaseRemoteRepository<MoviesResponse>() {
    fun searchMovies(query: String): Observable<List<Movie>> =
        getResult {
            service.searchMovie(query)
        }.map {  mr -> mr.results?.map { ResponseToMovieMapper.toVO(it) } }
}
