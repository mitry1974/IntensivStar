package ru.androidschool.intensiv.domain.models

import androidx.room.Entity

@Entity(tableName = "actors")
data class Actor(val name: String, val profilePath: String)
