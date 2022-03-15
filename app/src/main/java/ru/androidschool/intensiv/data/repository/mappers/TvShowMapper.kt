package ru.androidschool.intensiv.data.repository.mappers

import ru.androidschool.intensiv.api.model.TvShowResponse
import ru.androidschool.intensiv.data.entity.TvShow

object TvShowMapper: VOMapper<TvShowResponse, TvShow> {
    override fun toVO(dto: TvShowResponse): TvShow =
        TvShow(dto.id ?: 0, dto.name.orEmpty(), dto.voteAverage ?: 0F, dto.posterPath.orEmpty())
}