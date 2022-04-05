package ru.androidschool.intensiv.data.repository.favorites

import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.local.database.MoviesDatabase
import ru.androidschool.intensiv.data.mappers.EntityToMovieMapper
import ru.androidschool.intensiv.data.mappers.FavoriteToEntityMapper
import ru.androidschool.intensiv.domain.models.Favorite
import ru.androidschool.intensiv.domain.models.Movie
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val database: MoviesDatabase) {
    fun getFavoriteMovies(): Observable<List<Movie>> =
        database.favoritesDao().getFavoriteMovies()
            .map { it.map { fe -> EntityToMovieMapper.toVO(fe) } }

    fun updateFavoriteStatus(movieId: Int, isChecked: Boolean): Completable =
        if (isChecked) {
            database.favoritesDao()
                .insertFavorite(FavoriteToEntityMapper.toEntity(Favorite(movieId)))
        } else {
            database.favoritesDao().getFavoriteByMovieId(movieId).flatMapCompletable { favorite ->
                database.favoritesDao().deleteFavorite(FavoriteToEntityMapper.toEntity(favorite))
            }
        }

    fun getFavoriteByMovieId(movieId: Int): Observable<Favorite> =
        database.favoritesDao().getFavoriteByMovieId(movieId)
}
