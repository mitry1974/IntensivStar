package ru.androidschool.intensiv.data.repository.movies

import ru.androidschool.intensiv.api.MoviesInterface
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.data.entity.*

class MoviesRepository private constructor(private val remoteDatasource: MoviesRemoteDatasource) {
    lateinit var nowPlaying: Map<Int, Movie>
    lateinit var upcoming: Map<Int, Movie>
    lateinit var popular: Map<Int, Movie>

    private fun mapMovieResponseToMovies(moviesResponse: MoviesResponse): Map<Int, Movie> =
        moviesResponse.results.associate { it.id to Movie(it) }


    suspend fun getNowPlaying() {
        val result = remoteDatasource.loadNowPlaying()
        return if (result is Result.Success && result.successed) {
            nowPlaying = mapMovieResponseToMovies(result.data)
        } else {
            throw Exception("Error loading now playing films")
        }
    }

    suspend fun getUpcoming() {
        val result = remoteDatasource.loadUpcoming()
        return if (result is Result.Success && result.successed) {
            upcoming = mapMovieResponseToMovies(result.data)
        } else {
            throw Exception("Error loading now upcoming films")
        }
    }

    suspend fun getPopular() {
        val result = remoteDatasource.loadPopular()
        return if (result is Result.Success && result.successed) {
            popular = mapMovieResponseToMovies(result.data)
        } else {
            throw Exception("Error loading now upcoming films")
        }
    }

    suspend fun getCredits(movieId: Int): List<Actor> {
        val result = remoteDatasource.loadCredits(movieId)
        return if (result is Result.Success && result.successed) {
            result.data.cast.map { Actor(it.name, it.profilePath) }
        } else {
            throw Exception("Error loading now upcoming films")
        }
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val result = remoteDatasource.loadMovieDetails(movieId)
        return if (result is Result.Success && result.successed) {
            MovieDetails(result.data)
        } else {
            throw Exception("Error loading movie details")
        }
    }

    suspend fun getTvShows(): List<TvShow> {
        val result = remoteDatasource.loadTvShows()
        return if (result is Result.Success && result.successed) {
            result.data.results.map { TvShow(it.id, it.name, it.voteAverage, it.posterPath)}
        } else {
            throw Exception("Error loading movie details")
        }
    }



    companion object {
        fun getRepository(): MoviesRepository = MoviesRepository(
            MoviesRemoteDatasource(
                MoviesInterface.apiClient
            )
        )
    }
}