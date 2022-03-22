package ru.androidschool.intensiv.domain.interactors

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.local.database.MoviesDatabase
import ru.androidschool.intensiv.data.repository.movies.feedRepository.NowPlayingMoviesRepository
import ru.androidschool.intensiv.data.repository.movies.feedRepository.PopularMoviesRepository
import ru.androidschool.intensiv.data.repository.movies.feedRepository.UpcomingMoviesRepository
import ru.androidschool.intensiv.domain.MoviesRepository
import ru.androidschool.intensiv.domain.models.Movie

class MoviesInteractor(
    private val nowPlayingMoviesRepository: MoviesRepository,
    private val popularMoviesRepository: MoviesRepository,
    private val upcomingMoviesRepository: MoviesRepository
) : CacheInteractor<Map<Int, List<Movie>>>(AndroidSchedulers.mainThread(), Schedulers.io()) {

    override fun createOfflineObservable(): Observable<Map<Int, List<Movie>>> =
        ObservableInteractor.createLocalObservable(
            nowPlayingMoviesRepository,
            popularMoviesRepository,
            upcomingMoviesRepository
        )

    override fun createRemoteObservable(): Observable<Map<Int, List<Movie>>> =
        ObservableInteractor.createRemoteObservable(
            nowPlayingMoviesRepository,
            popularMoviesRepository,
            upcomingMoviesRepository
        )

    override fun saveDataLocal(data: Map<Int, List<Movie>>) {
        data[R.string.upcoming]?.let {upcomingMoviesRepository.saveLocalItems(it)}
        data[R.string.popular]?.let {popularMoviesRepository.saveLocalItems(it)}
        data[R.string.recommended]?.let {nowPlayingMoviesRepository.saveLocalItems(it)}
    }

    companion object Factory {
        fun build(context: Context) = MoviesInteractor(
            NowPlayingMoviesRepository(
                TMDBInterface.apiClient,
                MoviesDatabase.buildDatabase(context)
            ),
            PopularMoviesRepository(
                TMDBInterface.apiClient,
                MoviesDatabase.buildDatabase(context)
            ),
            UpcomingMoviesRepository(
                TMDBInterface.apiClient,
                MoviesDatabase.buildDatabase(context)
            )
        )
    }
}
