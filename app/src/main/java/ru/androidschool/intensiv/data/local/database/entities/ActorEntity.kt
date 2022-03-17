package ru.androidschool.intensiv.data.local.database.entities

import androidx.room.Entity

@Entity
data class ActorEntity(val name: String, val profilePath: String)
