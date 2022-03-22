package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.local.database.entities.ActorEntity
import ru.androidschool.intensiv.domain.models.Actor

object EntityToActorMapper : IVOMapper<ActorEntity, Actor> {
    override fun toVO(dto: ActorEntity): Actor =
        Actor(dto.name, dto.profilePath)
}
