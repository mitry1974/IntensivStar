package ru.androidschool.intensiv.util.extensions

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

fun <T> applyObservableTransformer(subscribeOn: Scheduler, observeOn: Scheduler): ObservableTransformer<T, T> {
    return ObservableTransformer { observable ->  observable.subscribeOn(subscribeOn)
        .observeOn(observeOn) }
}
