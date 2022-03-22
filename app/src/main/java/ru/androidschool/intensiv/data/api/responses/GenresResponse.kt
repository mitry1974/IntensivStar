package ru.androidschool.intensiv.data.api.responses

data class GenreResponse(
    val name: String?
)
data class GenresResponse(
    val genres: List<GenreResponse>?
)
