package ru.androidschool.intensiv.data.repository.details

import io.reactivex.Observable
import ru.androidschool.intensiv.data.api.BaseRemoteRepository
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.api.responses.MovieDetailsResponse
import ru.androidschool.intensiv.data.mappers.ResponseToMovieDetailsMapper
import ru.androidschool.intensiv.domain.models.MovieDetails

class MovieDetailsRemoteRepository(private val service: TMDBInterface) :
    BaseRemoteRepository<MovieDetailsResponse>() {
    fun loadItemDetails(id: Int): Observable<MovieDetails> =
        getResult {
            service.loadMovieDetails(id)
        }.map { mdr -> ResponseToMovieDetailsMapper.toVO(mdr) }
}
