package ru.androidschool.intensiv.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_ids")
data class FavoriteEntity(@PrimaryKey val movieId: Int)
