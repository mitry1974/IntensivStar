package ru.androidschool.intensiv.presentation.feed

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.androidschool.intensiv.domain.interactors.MoviesInteractor

class FeedViewModel(private val context: Context): ViewModel() {
    private lateinit var moviesInteractor: MoviesInteractor

    init {
        moviesInteractor = MoviesInteractor.build(context)
    }

}