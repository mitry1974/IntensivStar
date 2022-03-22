package ru.androidschool.intensiv.data.repository.movies.feedRepository

import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.local.database.MoviesDatabase
import ru.androidschool.intensiv.data.local.database.entities.MovieType

class NowPlayingMoviesRepository(
    private val service: TMDBInterface,
    private val database: MoviesDatabase
) : FeedRepository(service, database, MovieType.NOW_PLAYING, { service.loadNowPlaying() })
