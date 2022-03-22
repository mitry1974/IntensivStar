package ru.androidschool.intensiv.domain.interactors

import io.reactivex.Observable
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.domain.MoviesRepository
import ru.androidschool.intensiv.domain.models.Movie

object ObservableInteractor {
    private fun createObservable(
        nowPlayingItems: Observable<List<Movie>>,
        popularMoviesItems: Observable<List<Movie>>,
        upcomingMoviesItems: Observable<List<Movie>>
    ): Observable<Map<Int, List<Movie>>> =
        Observable.zip(
            upcomingMoviesItems,
            nowPlayingItems,
            popularMoviesItems
        ) { u, n, p ->
            val mapTitleToList = mutableMapOf<Int, List<Movie>>()
            mapTitleToList[R.string.recommended] = n
            mapTitleToList[R.string.popular] = p
            mapTitleToList[R.string.upcoming] = u
            return@zip mapTitleToList.toMap()
        }

    fun createLocalObservable(
        nowPlayingMoviesRepository: MoviesRepository,
        popularMoviesRepository: MoviesRepository,
        upcomingMoviesRepository: MoviesRepository
    ): Observable<Map<Int, List<Movie>>> =
        createObservable(
            nowPlayingMoviesRepository.getLocalItems(),
            popularMoviesRepository.getLocalItems(),
            upcomingMoviesRepository.getLocalItems()
        )

    fun createRemoteObservable(
        nowPlayingMoviesRepository: MoviesRepository,
        popularMoviesRepository: MoviesRepository,
        upcomingMoviesRepository: MoviesRepository
    ): Observable<Map<Int, List<Movie>>> =
        createObservable(
            nowPlayingMoviesRepository.loadItemsFromApi(),
            popularMoviesRepository.loadItemsFromApi(),
            upcomingMoviesRepository.loadItemsFromApi()
        )
}
