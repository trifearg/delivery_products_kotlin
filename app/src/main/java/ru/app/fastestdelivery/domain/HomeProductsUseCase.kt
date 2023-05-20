package ru.app.fastestdelivery.domain

import ru.app.fastestdelivery.data.Repository
import ru.app.fastestdelivery.domain.models.Product
import javax.inject.Inject

class HomeProductsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getHomeProductsFlow(): List<Product> = repository.getAllProducts()

    suspend fun insertBagProduct(product: Product) = repository.insertBagProduct(product)
}