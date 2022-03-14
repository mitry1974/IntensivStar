package ru.androidschool.intensiv.data.repository.mappers

import ru.androidschool.intensiv.api.model.MovieResponse
import ru.androidschool.intensiv.data.entity.Movie

object MovieMapper : VOMapper<MovieResponse, Movie> {
    override fun toVO(dto: MovieResponse): Movie =
        Movie(
            id = dto.id ?: 0,
            title = dto.title.orEmpty(),
            voteAverage = dto.voteAverage ?: 0F,
            posterPath = dto.posterPath.orEmpty()
        )
}