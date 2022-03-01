package ru.androidschool.intensiv.data.repository.movies

import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.MoviesInterface
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.CreditsResponse
import ru.androidschool.intensiv.api.model.GenresResponse
import ru.androidschool.intensiv.api.model.MoviesResponse

class MoviesRemoteDatasource(private val service: MoviesInterface): BaseRemoteDataSource() {
    suspend fun loadNowPlaying(): Result<MoviesResponse> =
        getResult {
            service.getNowPlaying()
        }

    suspend fun loadUpcoming(): Result<MoviesResponse> =
        getResult {
            service.getUpcoming()
        }

    suspend fun loadPopular(): Result<MoviesResponse> =
        getResult {
            service.getPopular()
        }

    suspend fun loadGenres(): Result<GenresResponse> =
        getResult {
            service.getGenres()
        }

    suspend fun loadCredits(movieId: Int): Result<CreditsResponse> =
        getResult {
            service.getMovieCredits(movieId)
        }
}