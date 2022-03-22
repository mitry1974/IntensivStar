package ru.androidschool.intensiv.data.local.database.dao

import androidx.room.*
import io.reactivex.Observable
import ru.androidschool.intensiv.data.local.database.entities.MovieEntity
import ru.androidschool.intensiv.data.local.database.entities.MovieType
import ru.androidschool.intensiv.domain.models.Movie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMovies(type: MovieType): Observable<List<Movie>>

    @Query("SELECT * FROM movies where id = :id")
    fun getMovieByID(id: Int): Observable<MovieEntity>

    @Update
    fun updateMovie(movie: MovieEntity): Int
}
