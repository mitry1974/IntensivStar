package ru.androidschool.intensiv.data.repository.movies

import io.reactivex.Observable
import retrofit2.Response
import ru.androidschool.intensiv.data.api.BaseRemoteRepository
import ru.androidschool.intensiv.data.api.responses.MoviesResponse
import ru.androidschool.intensiv.data.mappers.ResponseToMovieMapper
import ru.androidschool.intensiv.domain.MoviesRepository
import ru.androidschool.intensiv.domain.models.Movie

abstract class BaseMoviesRepository: MoviesRepository {
    private val baseRemoteRepository = BaseRemoteRepository<MoviesResponse>()

    fun getResult(call: () -> Observable<Response<MoviesResponse>>): Observable<List<Movie>> =
        baseRemoteRepository.getResult(call).map {  mr -> mr.results?.map { ResponseToMovieMapper.toVO(it) } }
}