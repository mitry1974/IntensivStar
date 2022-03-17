package ru.androidschool.intensiv.api.responses

data class GenreResponse(
    val name: String?
)
data class GenresResponse(
    val genres: List<GenreResponse>?
)
