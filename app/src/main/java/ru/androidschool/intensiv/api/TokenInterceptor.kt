package ru.androidschool.intensiv.api

import okhttp3.Interceptor
import okhttp3.Response
import ru.androidschool.intensiv.util.Constants

class TokenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("api_key", Constants.MOVIE_API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}