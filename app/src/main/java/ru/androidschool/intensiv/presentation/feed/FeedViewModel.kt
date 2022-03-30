package ru.androidschool.intensiv.presentation.feed

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.data.repository.RepositoryAccess
import ru.androidschool.intensiv.domain.interactors.MoviesInteractor
import ru.androidschool.intensiv.domain.models.Movie

@SuppressLint("CheckResult")
class FeedViewModel(private var moviesInteractor: MoviesInteractor) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _feedData = MutableLiveData<Map<Int, List<Movie>>>()
    val feedData: LiveData<Map<Int, List<Movie>>> = _feedData

    init {
        moviesInteractor.getObservable(RepositoryAccess.OFFLINE_FIRST)
            .doOnError { _error.postValue(it.message) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _isLoading.postValue(true) }
            .doFinally {
                _isLoading.postValue(false)
            }
            .subscribe {
                _feedData.postValue(it)
            }
    }
}