package ru.androidschool.intensiv.data.repository.mappers

import ru.androidschool.intensiv.api.responses.CreditResponse
import ru.androidschool.intensiv.models.Actor

object ResponseToActorMapper : VOMapper<CreditResponse, Actor> {
    override fun toVO(dto: CreditResponse): Actor =
        Actor(dto.name, dto.profilePath.orEmpty())
}