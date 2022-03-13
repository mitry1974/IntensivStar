package ru.androidschool.intensiv.data.repository.mappers

import ru.androidschool.intensiv.api.model.MovieDetailsResponse
import ru.androidschool.intensiv.data.entity.MovieDetails
import ru.androidschool.intensiv.util.extensions.getYear

object MovieDetailsMapper : VOMapper<MovieDetailsResponse, MovieDetails> {
    override fun toVO(dto: MovieDetailsResponse): MovieDetails =
        MovieDetails(
            id = dto.id ?: 0,
            title = dto.title.orEmpty(),
            voteAverage = dto.voteAverage ?: 0F,
            productionCompanies = dto.productionCompanies?.joinToString(", ").orEmpty(),
            genres = dto.genres?.joinToString(", ").orEmpty(),
            overview = dto.overview ?: "",
            year = dto.releaseDate?.getYear().orEmpty(),
            posterPath = dto.posterPath.orEmpty()
        )
}