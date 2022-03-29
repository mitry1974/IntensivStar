package ru.androidschool.intensiv.presentation.tvshows

import android.annotation.SuppressLint
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.data.repository.tvShows.TvShowsRepository
import ru.androidschool.intensiv.domain.models.TvShow
import ru.androidschool.intensiv.presentation.BasePresenter

class TvShowsPresenter(private val repository: TvShowsRepository) :
    BasePresenter<TvShowsPresenter.TvShowsView>() {

    @SuppressLint("CheckResult")
    fun loadTvShows() {
        repository.loadItemsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.showLoader() }
            .doFinally { view?.hideLoader() }
            .subscribe(
                { view?.showTvShows(it) },
                { t -> view?.showToast("Loading TvShows error: $t") })
    }

    interface TvShowsView {
        fun showTvShows(tvShows: List<TvShow>)
        fun showLoader()
        fun hideLoader()
        fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT)
    }
}
