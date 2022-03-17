package ru.androidschool.intensiv.api

import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.api.responses.*
import ru.androidschool.intensiv.util.Constants

interface TMDBInterface {
    @GET("movie/now_playing")
    fun loadNowPlaying(): Observable<Response<MoviesResponse>>

    @GET("movie/upcoming")
    fun loadUpcoming(): Observable<Response<MoviesResponse>>

    @GET("movie/popular")
    fun loadPopular(): Observable<Response<MoviesResponse>>

    @GET("movie/{movie_id}/credits")
    fun loadMovieCredits(@Path("movie_id") movieId: Int): Observable<Response<CreditsResponse>>

    @GET("genre/movie/list")
    fun loadGenres(): Observable<Response<GenresResponse>>

    @GET("movie/{movie_id}")
    fun loadMovieDetails(@Path("movie_id") movieId: Int): Observable<Response<MovieDetailsResponse>>

    @GET("tv/popular")
    fun loadTvShows(): Observable<Response<TvShowsListResponse>>

    @GET("search/movie")
    fun searchMovie(
        @Query("query") query: String
    ): Observable<Response<MoviesResponse>>


    companion object Factory {
        private fun getOkHttpClient(): OkHttpClient {
            val requestInterceptor = Interceptor { chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .header("api_key", Constants.MOVIE_API_KEY)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            val builder = OkHttpClient.Builder().addInterceptor(TokenInterceptor())

            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                builder.addInterceptor(loggingInterceptor)
            }

            return builder.addInterceptor(TokenInterceptor())
                .build()
        }

        val apiClient: TMDBInterface by lazy {
            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL_RETROFIT_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()

            return@lazy retrofit.create(TMDBInterface::class.java)
        }
    }
}