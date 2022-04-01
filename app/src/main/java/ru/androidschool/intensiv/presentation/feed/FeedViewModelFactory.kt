package ru.androidschool.intensiv.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androidschool.intensiv.domain.interactors.MoviesInteractor

class FeedViewModelFactory(val moviesInteractor: MoviesInteractor): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesInteractor::class.java)
            .newInstance(moviesInteractor)
    }
}