package ru.app.fastestdelivery.data.converters.product

import ru.app.fastestdelivery.data.models.database.ProductEntity
import ru.app.fastestdelivery.data.models.network.ProductResponseModel
import javax.inject.Inject

class ProductResponseToEntity @Inject constructor() {

    fun convert(response: ProductResponseModel) = ProductEntity(
        id = response.id,
        name = response.name,
        quantity = response.quantity,
        price = response.price,
        description = response.description,
        type = response.type,
        energyValue = response.energyValue,
        weight = response.weight,
        photoName = response.photos.firstOrNull()?.photoName.orEmpty(),
        photoUrl = response.photos.firstOrNull()?.photo.orEmpty(),
    )

}