package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.api.responses.TvShowResponse
import ru.androidschool.intensiv.domain.models.TvShow

object ResponseToTvShowMapper : IVOMapper<TvShowResponse, TvShow> {
    override fun toVO(dto: TvShowResponse): TvShow =
        TvShow(dto.id ?: 0, dto.name.orEmpty(), dto.voteAverage ?: 0F, dto.posterPath.orEmpty())
}
