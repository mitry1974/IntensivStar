package ru.androidschool.intensiv.data.repository.movies.favorites

import io.reactivex.Observable
import ru.androidschool.intensiv.data.local.database.dao.FavoritesDao
import ru.androidschool.intensiv.models.Movie

class FavoritesLocalDataSource(dao: FavoritesDao) {
    fun getFavorites(): Observable<List<Movie>> {
        return listOf()
    }

    fun deleteFavorite(id: Int) {

    }


    fun addFavorite(movie: Movie) {

    }
}
