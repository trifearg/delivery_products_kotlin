package ru.app.fastestdelivery.data.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val BAG_PRODUCT_TABLE = "bagProductTable"

@Entity(tableName = BAG_PRODUCT_TABLE)
data class BagProductEntity(
    @PrimaryKey
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