package ru.androidschool.intensiv.data.repository.mappers

import ru.androidschool.intensiv.api.responses.CreditResponse
import ru.androidschool.intensiv.data.local.database.entities.ActorEntity
import ru.androidschool.intensiv.models.Actor

object EntityToActorMapper: VOMapper<ActorEntity, Actor> {
    override fun toVO(dto: ActorEntity): Actor =
        Actor(dto.name, dto.profilePath)
}