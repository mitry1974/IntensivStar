package ru.androidschool.intensiv.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.androidschool.intensiv.api.model.CreditsResponse
import ru.androidschool.intensiv.api.model.GenresResponse
import ru.androidschool.intensiv.api.model.MovieDetailsResponse
import ru.androidschool.intensiv.api.model.MoviesResponse
import ru.androidschool.intensiv.util.Constants

interface MoviesInterface {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcoming(): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopular(): Response<MoviesResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movieId") movieId: Int): Response<CreditsResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenresResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetails(@Path("movieId") movieId: Int): Response<MovieDetailsResponse>

    companion object Factory {
        private fun getOkHttpClient(): OkHttpClient {
             val requestInterceptor = Interceptor { chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .header("api_key", Constants.MOVIE_API_KEY)

                val request = requestBuilder.build()
                chain.proceed(request)
            }

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder().addInterceptor(TokenInterceptor())
                .addInterceptor(loggingInterceptor).build()
        }
        val apiClient: MoviesInterface by lazy {
            val retrofit = retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL_RETROFIT_API)
                .client(getOkHttpClient())
                .build()

            return@lazy retrofit.create(MoviesInterface::class.java)
        }
    }
}