package ru.app.fastestdelivery.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.app.fastestdelivery.data.models.database.BagProductEntity
import ru.app.fastestdelivery.data.models.database.ProductEntity
import ru.app.fastestdelivery.data.models.database.UserEntity

@Database(entities = [UserEntity::class, ProductEntity::class, BagProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao

    abstract fun bagProductDao(): BagProductDao

}