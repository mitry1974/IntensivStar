package ru.androidschool.intensiv.api.model

data class GenreResponse(
    val id: Int,
    val genre: String
)
data class GenresResponse(
    val genres: List<GenreResponse>
)
