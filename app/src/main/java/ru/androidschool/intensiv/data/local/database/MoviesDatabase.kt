package ru.androidschool.intensiv.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.androidschool.intensiv.data.local.database.dao.FavoritesDao
import ru.androidschool.intensiv.data.local.database.dao.MoviesDao
import ru.androidschool.intensiv.data.local.database.entities.ActorEntity
import ru.androidschool.intensiv.data.local.database.entities.FavoriteEntity
import ru.androidschool.intensiv.data.local.database.entities.MovieEntity
import ru.androidschool.intensiv.data.local.database.typeconverters.MovieTypeConverter

@Database(
    entities = [MovieEntity::class, ActorEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MovieTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    abstract fun favoritesDao(): FavoritesDao

    companion object Factory {
        fun buildDatabase(context: Context): MoviesDatabase =
            Room.databaseBuilder(context, MoviesDatabase::class.java, "Movies").build()
    }
}
