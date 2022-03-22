package ru.androidschool.intensiv.data.api.responses

import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.util.Constants

data class CreditResponse(
    val name: String
) {
    @SerializedName("profile_path")
    val profilePath: String? = ""
        get() = "${Constants.IMAGE_URL}$field"
}

data class CreditsResponse(
    val cast: List<CreditResponse>?
)
