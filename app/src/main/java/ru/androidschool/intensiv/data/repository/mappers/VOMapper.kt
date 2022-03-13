package ru.androidschool.intensiv.data.repository.mappers

interface VOMapper<P, Q> {
    fun toVO(dto: P): Q
    fun toVO(list: Collection<P>): List<Q> =
        list.map { toVO(it) }
}
