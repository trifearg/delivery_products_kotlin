package ru.app.fastestdelivery.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.app.fastestdelivery.data.models.database.USER_TABLE
import ru.app.fastestdelivery.data.models.database.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM $USER_TABLE")
    suspend fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM $USER_TABLE")
    suspend fun clearUser()

}