package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.local.database.entities.FavoriteEntity
import ru.androidschool.intensiv.domain.models.Favorite

object FavoriteToEntityMapper {
    fun toEntity(dto: Favorite) = FavoriteEntity(dto.movieId)
}