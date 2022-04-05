package ru.androidschool.intensiv.dagger.di

import dagger.Component
import ru.androidschool.intensiv.presentation.movie_details.MovieDetailsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelModule::class,
    DataModule::class,
    NetworkModule::class,
    AppModule::class])
interface MovieDetailsViewModelComponent {
    fun inject(fragment: MovieDetailsFragment)
}