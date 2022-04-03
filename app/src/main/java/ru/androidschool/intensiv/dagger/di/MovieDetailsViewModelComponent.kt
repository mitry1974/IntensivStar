package ru.androidschool.intensiv.dagger.di

import dagger.Component
import ru.androidschool.intensiv.dagger.di.view_model_factory.ViewModelModule
import ru.androidschool.intensiv.presentation.movie_details.MovieDetailsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    MovieDetailsRepositoryModule::class,
    FavoritesRepositoryModule::class,
    ViewModelModule::class,
    TMDIInterfaceModule::class,
    DatabaseModule::class, AppModule::class])
interface MovieDetailsViewModelComponent {
    fun inject(fragment: MovieDetailsFragment)
}