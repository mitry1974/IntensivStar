package ru.androidschool.intensiv.data.repository.movies.feedRepository

import io.reactivex.Observable
import retrofit2.Response
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.api.responses.MoviesResponse
import ru.androidschool.intensiv.data.local.database.MoviesDatabase
import ru.androidschool.intensiv.data.local.database.entities.MovieType
import ru.androidschool.intensiv.data.mappers.MovieToEntityMapper
import ru.androidschool.intensiv.data.repository.movies.BaseMoviesRepository
import ru.androidschool.intensiv.domain.models.Movie

open class FeedRepository(
    private val service: TMDBInterface,
    private val database: MoviesDatabase,
    private val type: MovieType,
    private val call: () -> Observable<Response<MoviesResponse>>,
) : BaseMoviesRepository() {
    override fun loadItemsFromApi(): Observable<List<Movie>> =
        getResult {
            call ()
        }

    override fun getLocalItems(): Observable<List<Movie>> =
        database.moviesDao().getMovies(type)

    override fun saveLocalItems(items: List<Movie>) {
        database.moviesDao()
            .insertMovies(items.map { MovieToEntityMapper.toEntity(it, type) })
    }
}
