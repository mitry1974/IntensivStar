package ru.androidschool.intensiv.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.api.model.*
import ru.androidschool.intensiv.util.Constants

interface TMDBInterface {
    @GET("movie/now_playing")
    fun loadNowPlaying(): Response<MoviesResponse>

    @GET("movie/upcoming")
    fun loadUpcoming(): Response<MoviesResponse>

    @GET("movie/popular")
    fun loadPopular(): Response<MoviesResponse>

    @GET("movie/{movie_id}/credits")
    fun loadMovieCredits(@Path("movie_id") movieId: Int): Response<CreditsResponse>

    @GET("genre/movie/list")
    fun loadGenres(): Response<GenresResponse>

    @GET("movie/{movie_id}")
    fun loadMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsResponse>

    @GET("tv/popular")
    fun loadTvShows(): Response<TvShowsListResponse>

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
                .client(getOkHttpClient())
                .build()

            return@lazy retrofit.create(TMDBInterface::class.java)
        }
    }
}