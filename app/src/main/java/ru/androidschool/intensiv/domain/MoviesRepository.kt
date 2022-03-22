package ru.androidschool.intensiv.domain

import io.reactivex.Observable
import ru.androidschool.intensiv.data.mappers.MovieToEntityMapper
import ru.androidschool.intensiv.domain.models.Movie

interface MoviesRepository {
    fun loadItemsFromApi(): Observable<List<Movie>>

    fun getLocalItems(): Observable<List<Movie>>

    fun saveLocalItems(items: List<Movie>)
}