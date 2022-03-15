package ru.androidschool.intensiv.data.repository.interactor
import io.reactivex.Observable
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.local.database.entity.Movie
import ru.androidschool.intensiv.data.repository.movies.nowPlaying.NowPlayingMoviesListRepository
import ru.androidschool.intensiv.data.repository.movies.popular.PopularMoviesListRepository
import ru.androidschool.intensiv.data.repository.movies.upcoming.UpcomingMoviesListRepository

class MoviesInteractor {
    private val nowPlayingRepository by lazy { NowPlayingMoviesListRepository()}
    private val popularMoviesListRepository by lazy { PopularMoviesListRepository() }
    private val upcomingMoviesListRepository by lazy { UpcomingMoviesListRepository() }
    fun getMoviesList(): Observable<Map<Int, List<Movie>>> {
        val nowPlaying = nowPlayingRepository.getItemsList()
        val popular = popularMoviesListRepository.getItemsList()
        val upcoming = upcomingMoviesListRepository.getItemsList()
        return Observable.zip(
            upcoming,
            nowPlaying,
            popular) { u, n, p ->

            val mapTitleToList = mutableMapOf<Int, List<Movie>>()
            mapTitleToList[R.string.recommended] = n
            mapTitleToList[R.string.popular] = p
            mapTitleToList[R.string.upcoming] = u
            return@zip mapTitleToList
        }
    }
}