package ru.androidschool.intensiv.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieResponse(
    val id: Int,
    @SerializedName("title")
    val title: String?      ,
    @SerializedName("release_date")
    val releaseDate: Date,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("genre_ids")
    val genreIds: List<Int>
)

data class MoviesResponse(
    val page: Int,
    val results: List<MovieResponse>
)
