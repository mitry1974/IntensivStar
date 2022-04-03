package ru.androidschool.intensiv.dagger.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.api.TMDBInterface

@Module
class TMDIInterfaceModule {
    @Provides
    fun providesTMDIInterface() = TMDBInterface.apiClient
}