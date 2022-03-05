package ru.androidschool.intensiv.api.model

data class GenreResponse(
    val name: String?
)
data class GenresResponse(
    val genres: List<GenreResponse>?
)
