//package ru.androidschool.intensiv.data.repository.movies
//
//import ru.androidschool.intensiv.api.TMDBInterface
//import ru.androidschool.intensiv.api.Result
//import ru.androidschool.intensiv.api.model.TvShowsListResponse
//import ru.androidschool.intensiv.api.successed
//import ru.androidschool.intensiv.data.entity.*
//
//class MoviesRepository private constructor(private val remoteDatasource: MoviesRemoteDatasource) {
//    lateinit var nowPlaying: Map<Int, Movie>
//    lateinit var upcoming: Map<Int, Movie>
//    lateinit var popular: Map<Int, Movie>
//
//
//
//    private fun mapTvShowResponseToTvShows(tvShowsResponse: TvShowsListResponse): List<TvShow> =
//        tvShowsResponse.results?.let { it ->
//            it.filter { value -> value.id != null }.map {
//                TvShow(
//                    id = it.id ?: 0,
//                    name = it.name ?: "",
//                    voteAverage = it.voteAverage ?: 0F,
//                    posterPath = it.posterPath ?: ""
//                )
//            }
//        } ?: listOf()
//
//    suspend fun getNowPlaying() {
//        val result = remoteDatasource.loadNowPlaying()
//        return if (result is Result.Success && result.successed) {
//            nowPlaying = mapMovieResponseToMovies(result.data)
//        } else {
//            throw Exception("Error loading now playing films")
//        }
//    }
//
//    suspend fun getUpcoming() {
//        val result = remoteDatasource.loadUpcoming()
//        return if (result is Result.Success && result.successed) {
//            upcoming = mapMovieResponseToMovies(result.data)
//        } else {
//            throw Exception("Error loading now upcoming films")
//        }
//    }
//
//    suspend fun getPopular() {
//        val result = remoteDatasource.loadPopular()
//        return if (result is Result.Success && result.successed) {
//            popular = mapMovieResponseToMovies(result.data)
//        } else {
//            throw Exception("Error loading now upcoming films")
//        }
//    }
//
//    suspend fun getCredits(movieId: Int): List<Actor> {
//        val result = remoteDatasource.loadCredits(movieId)
//        return if (result is Result.Success && result.successed) {
//            result.data.cast.map { Actor(it.name, it.profilePath) }
//        } else {
//            throw Exception("Error loading now upcoming films")
//        }
//    }
//
//    suspend fun getMovieDetails(movieId: Int): MovieDetails {
//        val result = remoteDatasource.loadMovieDetails(movieId)
//        return if (result is Result.Success && result.successed) {
//            MovieDetails(result.data)
//        } else {
//            throw Exception("Error loading movie details")
//        }
//    }
//
//    suspend fun getTvShows(): List<TvShow> {
//        val result = remoteDatasource.loadTvShows()
//        return if (result is Result.Success && result.successed && result.data.results != null) {
//            mapTvShowResponseToTvShows(result.data)
//        } else {
//            throw Exception("Error loading movie details")
//        }
//    }
//
//
//    companion object {
//        fun getRepository(): MoviesRepository = MoviesRepository(
//            MoviesRemoteDatasource(
//                TMDBInterface.apiClient
//            )
//        )
//    }
//}