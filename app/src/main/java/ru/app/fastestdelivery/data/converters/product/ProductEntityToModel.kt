package ru.app.fastestdelivery.data.converters.product

import ru.app.fastestdelivery.data.models.database.ProductEntity
import ru.app.fastestdelivery.domain.models.Product
import javax.inject.Inject

class ProductEntityToModel @Inject constructor() {

    fun convert(entity: ProductEntity) = Product(
        id = entity.id,
        name = entity.name,
        quantity = entity.quantity,
        price = entity.price,
        description = entity.description,
        type = entity.type,
        energyValue = entity.energyValue,
        weight = entity.weight,
        photoName = entity.photoName,
        photoUrl = entity.photoUrl
    )

}