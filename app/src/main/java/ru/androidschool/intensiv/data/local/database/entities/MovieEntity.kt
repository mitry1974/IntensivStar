package ru.androidschool.intensiv.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class MovieType {
    POPULAR,
    NOW_PLAYING,
    UPCOMING
}

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String = "",
    val voteAverage: Float,
    val posterPath: String,
    val type: MovieType
)
