package ru.androidschool.intensiv.data.repository.movies.common

import io.reactivex.Observable

interface ItemListRepositoryInterface<P, Q> {
    fun getItemsList(): Observable<List<Q>>
}