package ru.androidschool.intensiv.data.local.database.typeconverters

import androidx.room.TypeConverter
import ru.androidschool.intensiv.data.local.database.entities.MovieType

class MovieTypeConverter {
    @TypeConverter
    fun fromMovieType(type: MovieType): Int =
        when (type) {
            MovieType.POPULAR -> 0
            MovieType.NOW_PLAYING -> 1
            MovieType.UPCOMING -> 2
        }

    @TypeConverter
    fun toMovieType(data: Int): MovieType =
        when (data) {
            0 -> MovieType.POPULAR
            1 -> MovieType.NOW_PLAYING
            else -> MovieType.UPCOMING
        }
}
