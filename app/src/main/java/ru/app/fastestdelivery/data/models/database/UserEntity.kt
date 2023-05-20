package ru.app.fastestdelivery.data.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val USER_TABLE = "userTable"

@Entity(tableName = USER_TABLE)
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val token: String
)