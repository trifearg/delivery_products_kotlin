package ru.app.fastestdelivery.data.models.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RegisterResponseModel(
    val name: String,
    val userId: Int,
    val token: String
)