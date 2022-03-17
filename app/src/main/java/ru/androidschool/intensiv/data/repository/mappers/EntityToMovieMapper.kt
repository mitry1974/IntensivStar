package ru.androidschool.intensiv.data.repository.mappers

import ru.androidschool.intensiv.data.local.database.entities.FavoriteEntity
import ru.androidschool.intensiv.models.Movie

object EntityToMovieMapper : VOMapper<FavoriteEntity, Movie> {
    override fun toVO(dto: FavoriteEntity): Movie =
        Movie(
            id = dto.id ?: 0,
            title = dto.title.orEmpty(),
            voteAverage = dto.voteAverage ?: 0F,
            posterPath = dto.posterPath.orEmpty()
        )
}