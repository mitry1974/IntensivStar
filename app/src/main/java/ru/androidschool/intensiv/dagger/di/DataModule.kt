package ru.androidschool.intensiv.dagger.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.local.database.MoviesDatabase
import ru.androidschool.intensiv.data.repository.favorites.FavoritesRepository

@Module
class DataModule {
    @Provides
    fun providesDatabase(context: Context) = MoviesDatabase.buildDatabase(context)
}