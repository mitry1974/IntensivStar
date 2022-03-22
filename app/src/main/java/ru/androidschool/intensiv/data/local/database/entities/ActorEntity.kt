package ru.androidschool.intensiv.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")
data class ActorEntity(@PrimaryKey val id: Int, val name: String, val profilePath: String)
