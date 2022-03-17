package ru.androidschool.intensiv.data.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.androidschool.intensiv.models.Movie

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "vote_average")
    val voteAverage: Float,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,
)