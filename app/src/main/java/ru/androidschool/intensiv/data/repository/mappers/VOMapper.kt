package ru.androidschool.intensiv.data.repository.mappers

interface VOMapper<DTO, VO> {
    fun toVO(dto: DTO): VO
    fun toVO(list: Collection<DTO>): List<VO> =
        list.map { toVO(it) }
}
