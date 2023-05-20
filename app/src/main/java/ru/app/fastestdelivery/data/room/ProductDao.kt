package ru.app.fastestdelivery.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.app.fastestdelivery.data.models.database.PRODUCT_TABLE
import ru.app.fastestdelivery.data.models.database.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM $PRODUCT_TABLE WHERE id=:id")
    suspend fun getProduct(id: String): ProductEntity

}