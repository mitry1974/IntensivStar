package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.local.database.entities.MovieEntity
import ru.androidschool.intensiv.data.local.database.entities.MovieType
import ru.androidschool.intensiv.domain.models.Movie

object MovieToEntityMapper {
    fun toEntity(dto: Movie, type: MovieType): MovieEntity =
        MovieEntity(id = dto.id, title = dto.title, voteAverage = dto.voteAverage, posterPath = dto.posterPath, type = type)
}