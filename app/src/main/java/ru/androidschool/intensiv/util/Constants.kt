package ru.androidschool.intensiv.util

import ru.androidschool.intensiv.BuildConfig

object Constants {
    const val GENERIC_ERROR = "Произошла какая-то непредвиденная ошибка"

    const val MOVIE_API_KEY = BuildConfig.THE_MOVIE_DATABASE_API
    const val BASE_URL_RETROFIT_API: String = BuildConfig.SERVER_URL
    const val IMAGE_URL = BuildConfig.IMAGE_URL
    const val MIN_SEARCH_LENGTH = 3
}
