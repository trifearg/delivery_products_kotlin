package ru.app.fastestdelivery.data.models.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequestModel(
    val name: String,
    val email: String,
    val password: String
)