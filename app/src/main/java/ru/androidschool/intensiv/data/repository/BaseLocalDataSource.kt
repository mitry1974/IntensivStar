package ru.androidschool.intensiv.data.repository

import io.reactivex.Observable

abstract class BaseLocalDataSource<T> {
    abstract fun getItems(): Observable<List<T>>
}
