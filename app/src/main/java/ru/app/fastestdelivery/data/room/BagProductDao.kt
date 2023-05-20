package ru.app.fastestdelivery.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.app.fastestdelivery.data.models.database.BAG_PRODUCT_TABLE
import ru.app.fastestdelivery.data.models.database.BagProductEntity

@Dao
interface BagProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBagProduct(product: BagProductEntity)

    @Query("SELECT * FROM $BAG_PRODUCT_TABLE")
    fun getBagProductsFlow(): Flow<List<BagProductEntity>>

    @Query("DELETE FROM $BAG_PRODUCT_TABLE WHERE quantity=0")
    suspend fun deleteEmptyBagProducts()

    @Query("DELETE FROM $BAG_PRODUCT_TABLE WHERE id=:id")
    suspend fun deleteBagProduct(id: Int)

    @Query("DELETE FROM $BAG_PRODUCT_TABLE")
    suspend fun deleteProductsFromBag()

}