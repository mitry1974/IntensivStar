package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.api.responses.MovieDetailsResponse
import ru.androidschool.intensiv.domain.models.MovieDetails
import ru.androidschool.intensiv.util.extensions.getYear

object ResponseToMovieDetailsMapper : IVOMapper<MovieDetailsResponse, MovieDetails> {
    override fun toVO(dto: MovieDetailsResponse): MovieDetails =
        MovieDetails(
            id = dto.id ?: 0,
            title = dto.title.orEmpty(),
            voteAverage = dto.voteAverage?.let{ dto.voteAverage / 2 } ?: 0F,
            productionCompanies = dto.productionCompanies?.joinToString(", ").orEmpty(),
            genres = dto.genres?.joinToString(", ").orEmpty(),
            overview = dto.overview ?: "",
            year = dto.releaseDate?.getYear().orEmpty(),
            posterPath = dto.posterPath.orEmpty()
        )
}
