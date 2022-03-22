package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.local.database.entities.FavoriteEntity
import ru.androidschool.intensiv.domain.models.Favorite

object EntityToFavoriteMapper: IVOMapper<FavoriteEntity, Favorite> {
    override fun toVO(dto: FavoriteEntity): Favorite = Favorite(dto.movieId)
}