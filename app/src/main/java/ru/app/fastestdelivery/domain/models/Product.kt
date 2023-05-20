package ru.app.fastestdelivery.domain.models

data class Product(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Int,
    val description: String,
    val type: String,
    val energyValue: Int,
    val weight: Int,
    val photoName: String,
    val photoUrl: String
)