package ru.androidschool.intensiv.data.mappers

interface IVOMapper<DTO, VO> {
    fun toVO(dto: DTO): VO
    fun toVO(list: Collection<DTO>): List<VO> =
        list.map { toVO(it) }
}
