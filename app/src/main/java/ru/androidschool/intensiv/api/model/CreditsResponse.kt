package ru.androidschool.intensiv.api.model

data class CreditResponse(
    val name: String
)
data class CreditsResponse(
    val casts: List<CreditResponse>
)