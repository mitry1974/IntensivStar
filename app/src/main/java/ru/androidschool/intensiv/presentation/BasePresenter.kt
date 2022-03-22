package ru.androidschool.intensiv.presentation

abstract class BasePresenter<V> {
    protected var view: V? = null

    fun attach(view: V) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }
}
