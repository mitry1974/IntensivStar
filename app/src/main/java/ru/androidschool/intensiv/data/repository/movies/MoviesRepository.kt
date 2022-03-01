package ru.androidschool.intensiv.data.repository.movies

import ru.androidschool.intensiv.api.MoviesInterface
import ru.androidschool.intensiv.api.Result
import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.api.successed
import ru.androidschool.intensiv.data.entity.Actor
import ru.androidschool.intensiv.data.entity.Genre
import ru.androidschool.intensiv.data.entity.Movie

class MoviesRepository private constructor (val remoteDatasource: MoviesRemoteDatasource) {
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

    suspend fun getGenres(): List<Genre> {
        val result = remoteDatasource.loadGenres()
        return if (result is Result.Success && result.successed) {
            result.data.genres.map { Genre(it.id, it.genre) }
        } else {
            throw Exception("Error loading now upcoming films")
        }
    }

    suspend fun getCredits(movieId: Int): List<Actor> {
        val result = remoteDatasource.loadCredits(movieId)
        return if (result is Result.Success && result.successed) {
            result.data.casts.map { Actor(it.name) }
        } else {
            throw Exception("Error loading now upcoming films")
        }
    }

    companion object {
        fun getRepository(): MoviesRepository = MoviesRepository(MoviesRemoteDatasource(
            MoviesInterface.apiClient))
    }
}