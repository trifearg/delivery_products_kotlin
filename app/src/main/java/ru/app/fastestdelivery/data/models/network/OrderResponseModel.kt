package ru.app.fastestdelivery.data.models.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderResponseModel(
        val id: Int,
        @Json(name = "id_customer")
        val customerId: Int,
        val price: Double,
        val status: String
)