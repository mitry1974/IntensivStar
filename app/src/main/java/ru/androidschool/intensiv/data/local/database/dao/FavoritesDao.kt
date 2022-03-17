package ru.androidschool.intensiv.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.androidschool.intensiv.models.Movie

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    fun getFavorites()

    @Insert
    fun addFavorite(movie: Movie)

    @Delete
    fun deleteFavorite(id: Int)
}