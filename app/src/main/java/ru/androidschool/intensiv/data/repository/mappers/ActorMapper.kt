package ru.androidschool.intensiv.data.repository.mappers

import ru.androidschool.intensiv.api.model.CreditResponse
import ru.androidschool.intensiv.data.entity.Actor

object ActorMapper : VOMapper<CreditResponse, Actor> {
    override fun toVO(dto: CreditResponse): Actor =
        Actor(dto.name, dto.profilePath.orEmpty())
}