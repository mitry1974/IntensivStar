package ru.androidschool.intensiv.data.repository.movies.common

interface ItemListRepositoryInterface<P, Q> {
    fun mapListResponse(response: P): Map<Int, Q>
    fun getItemsList(): Map<Int, Q>
}