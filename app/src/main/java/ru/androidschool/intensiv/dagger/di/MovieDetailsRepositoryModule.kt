package ru.androidschool.intensiv.dagger.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.repository.credits.CreditsRemoteRepository
import ru.androidschool.intensiv.data.repository.details.MovieDetailsRemoteDataSource
import ru.androidschool.intensiv.data.repository.details.MovieDetailsRepository

@Module
open class MovieDetailsRepositoryModule {
    @Provides
    fun providesMovieDetailsRepository(
        remoteDetailsDataSource: MovieDetailsRemoteDataSource,
        remoteActorsDataSource: CreditsRemoteRepository
    ) = MovieDetailsRepository(remoteDetailsDataSource, remoteActorsDataSource)
}