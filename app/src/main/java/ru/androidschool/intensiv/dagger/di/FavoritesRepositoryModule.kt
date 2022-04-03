package ru.androidschool.intensiv.dagger.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.local.database.MoviesDatabase
import ru.androidschool.intensiv.data.repository.favorites.FavoritesRepository

@Module
class FavoritesRepositoryModule {
    @Provides
    fun providesFavoritesRepository(database: MoviesDatabase) = FavoritesRepository(database)
}