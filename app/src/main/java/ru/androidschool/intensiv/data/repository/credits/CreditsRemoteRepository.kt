package ru.androidschool.intensiv.data.repository.credits

import io.reactivex.Observable
import ru.androidschool.intensiv.data.api.BaseRemoteRepository
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.api.responses.CreditsResponse
import ru.androidschool.intensiv.data.mappers.ResponseToActorMapper
import ru.androidschool.intensiv.domain.models.Actor
import javax.inject.Inject

class CreditsRemoteRepository @Inject constructor(private val service: TMDBInterface) :
    BaseRemoteRepository<CreditsResponse>() {
    fun loadCredits(movieId: Int): Observable<List<Actor>> =
        getResult {
            service.loadMovieCredits(movieId)
        }.map { cr ->
            cr.cast?.let { it -> ResponseToActorMapper.toVO(it) }
        }
}
