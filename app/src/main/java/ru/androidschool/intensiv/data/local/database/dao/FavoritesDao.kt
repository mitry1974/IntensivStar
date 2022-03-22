package ru.androidschool.intensiv.data.local.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.local.database.entities.FavoriteEntity
import ru.androidschool.intensiv.data.local.database.entities.MovieEntity
import ru.androidschool.intensiv.domain.models.Favorite

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites_ids")
    fun getFavorites(): Observable<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteEntity): Completable

    @Delete
    fun deleteFavorite(favorite: FavoriteEntity): Completable

    @Query("SELECT * FROM favorites_ids where movieId = :movieId")
    fun getFavoriteByMovieId(movieId: Int): Observable<Favorite>

    @Query("SELECT * FROM movies, favorites_ids WHERE movies.id = favorites_ids.movieId")
    fun getFavoriteMovies(): Observable<List<MovieEntity>>
}
