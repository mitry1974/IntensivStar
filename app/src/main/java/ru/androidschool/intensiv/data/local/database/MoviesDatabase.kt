package ru.androidschool.intensiv.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.androidschool.intensiv.data.local.database.dao.FavoritesDao
import ru.androidschool.intensiv.models.Actor
import ru.androidschool.intensiv.data.local.database.entities.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class, Actor::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

    companion object Factory {
        fun create(context: Context): MoviesDatabase =
            Room.databaseBuilder(context, MoviesDatabase::class.java, "Cities").build()
    }
}