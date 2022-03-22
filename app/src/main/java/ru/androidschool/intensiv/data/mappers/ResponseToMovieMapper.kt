package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.api.responses.MovieResponse
import ru.androidschool.intensiv.domain.models.Movie

object ResponseToMovieMapper : IVOMapper<MovieResponse, Movie> {
    override fun toVO(dto: MovieResponse): Movie =
        Movie(
            id = dto.id ?: 0,
            title = dto.title.orEmpty(),
            voteAverage = dto.voteAverage ?: 0F,
            posterPath = dto.posterPath.orEmpty()
        )
}
