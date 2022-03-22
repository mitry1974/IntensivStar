package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.api.responses.CreditResponse
import ru.androidschool.intensiv.domain.models.Actor

object ResponseToActorMapper : IVOMapper<CreditResponse, Actor> {
    override fun toVO(dto: CreditResponse): Actor =
        Actor(dto.name, dto.profilePath.orEmpty())
}
