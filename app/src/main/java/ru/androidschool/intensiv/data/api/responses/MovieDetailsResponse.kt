package ru.androidschool.intensiv.data.api.responses

import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.util.Constants

data class MovieDetailsResponse(
    val id: Int?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>?,
    val genres: List<GenreResponse>?,
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?
) {
    @SerializedName("poster_path")
    val posterPath: String = ""
        get() = "${Constants.IMAGE_URL}$field"
}
