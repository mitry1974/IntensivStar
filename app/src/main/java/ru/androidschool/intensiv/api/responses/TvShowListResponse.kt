package ru.androidschool.intensiv.api.responses

import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.util.Constants

data class TvShowResponse(
    val id: Int?,
    val name: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?
) {
    @SerializedName("poster_path")
    val posterPath: String? = ""
    get() = "${Constants.IMAGE_URL}$field"
}

data class TvShowsListResponse(
    val page: Int?,
    val results: List<TvShowResponse>?
)