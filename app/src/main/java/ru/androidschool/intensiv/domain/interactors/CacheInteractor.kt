package ru.androidschool.intensiv.domain.interactors

import io.reactivex.Observable
import io.reactivex.Scheduler
import ru.androidschool.intensiv.data.repository.RepositoryAccess

abstract class CacheInteractor<T>(private val uiScheduler: Scheduler, private val backgroundScheduler: Scheduler) {
    fun getObservable(type: RepositoryAccess): Observable<T> =
        createObservable(type)
            .subscribeOn(backgroundScheduler)
            .observeOn(uiScheduler)

    private fun createObservable(type: RepositoryAccess): Observable<T> =
        when (type) {
            RepositoryAccess.OFFLINE -> createOfflineObservable()
            RepositoryAccess.REMOTE_FIRST -> createRemoteObservable()
            RepositoryAccess.OFFLINE_FIRST -> {
                val remoteObservable = createRemoteObservable()
                createRemoteObservable()
                    .onErrorResumeNext(remoteObservable)
                    .switchIfEmpty(remoteObservable)
                    .map { data ->
                        saveDataLocal(data)
                        data
                    }
            }
            else -> {
                val remoteObservable = createRemoteObservable()
                createRemoteObservable()
                    .onErrorResumeNext(remoteObservable)
                    .concatWith(remoteObservable)
            }
        }

    protected abstract fun createOfflineObservable(): Observable<T>
    protected abstract fun createRemoteObservable(): Observable<T>
    protected abstract fun saveDataLocal(data: T)
}
