package ru.app.fastestdelivery.domain

import kotlinx.coroutines.flow.Flow
import ru.app.fastestdelivery.data.Repository
import ru.app.fastestdelivery.domain.models.Product
import javax.inject.Inject

class BagProductsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getBagProductsFlow(): Flow<List<Product>> = repository.getBagProductsFlow()

    suspend fun insertBagProduct(product: Product) = repository.insertBagProduct(product)

    suspend fun deleteBagProduct(id: Int) = repository.deleteBagProduct(id)

    suspend fun deleteBagProducts() = repository.deleteBagProducts()

}