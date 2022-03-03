package ru.androidschool.intensiv.data.repository.movies

import ru.androidschool.intensiv.api.BaseRemoteDataSource
import ru.androidschool.intensiv.api.MoviesInterface
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.*

class MoviesRemoteDatasource(private val service: MoviesInterface) : BaseRemoteDataSource() {
    suspend fun loadNowPlaying(): Result<MoviesResponse> =
        getResult {
            service.loadNowPlaying()
        }

    suspend fun loadUpcoming(): Result<MoviesResponse> =
        getResult {
            service.loadUpcoming()
        }

    suspend fun loadPopular(): Result<MoviesResponse> =
        getResult {
            service.loadPopular()
        }

    suspend fun loadGenres(): Result<GenresResponse> =
        getResult {
            service.loadGenres()
        }

    suspend fun loadCredits(movieId: Int): Result<CreditsResponse> =
        getResult {
            service.loadMovieCredits(movieId)
        }

    suspend fun loadMovieDetails(movieId: Int): Result<MovieDetailsResponse> =
        getResult {
            service.loadMovieDetails(movieId)
        }

    suspend fun loadTvShows(): Result<TvShowsListResponse> =
        getResult {
            service.loadTvShows()
        }
}