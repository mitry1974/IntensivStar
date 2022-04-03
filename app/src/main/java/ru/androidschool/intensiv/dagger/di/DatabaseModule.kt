package ru.androidschool.intensiv.dagger.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.local.database.MoviesDatabase

@Module
class DatabaseModule() {
    @Provides
    fun providesDatabase(context: Context) = MoviesDatabase.buildDatabase(context)
}