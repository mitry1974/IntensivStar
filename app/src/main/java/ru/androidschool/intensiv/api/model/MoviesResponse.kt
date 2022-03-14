package ru.androidschool.intensiv.api.model

import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.util.Constants
import java.util.*

data class MovieResponse(
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
) {
    @SerializedName("poster_path")
    val posterPath: String? = ""
        get() = "${Constants.IMAGE_URL}$field"
}

data class MoviesResponse(
    val page: Int?,
    val results: List<MovieResponse>?
)
