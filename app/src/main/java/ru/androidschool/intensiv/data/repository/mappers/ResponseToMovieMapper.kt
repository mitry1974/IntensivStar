package ru.androidschool.intensiv.data.repository.mappers

import ru.androidschool.intensiv.api.responses.MovieResponse
import ru.androidschool.intensiv.models.Movie

object ResponseToMovieMapper : VOMapper<MovieResponse, Movie> {
    override fun toVO(dto: MovieResponse): Movie =
        Movie(
            id = dto.id ?: 0,
            title = dto.title.orEmpty(),
            voteAverage = dto.voteAverage ?: 0F,
            posterPath = dto.posterPath.orEmpty()
        )
}