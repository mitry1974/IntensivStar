package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.local.database.entities.MovieEntity
import ru.androidschool.intensiv.domain.models.Movie

object EntityToMovieMapper : IVOMapper<MovieEntity, Movie> {
    override fun toVO(dto: MovieEntity): Movie =
        Movie(
            id = dto.id,
            title = dto.title,
            voteAverage = dto.voteAverage,
            posterPath = dto.posterPath
        )
}
