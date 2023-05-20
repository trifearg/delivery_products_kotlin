package ru.app.fastestdelivery.data.converters.product

import ru.app.fastestdelivery.data.models.database.BagProductEntity
import ru.app.fastestdelivery.domain.models.Product
import javax.inject.Inject

class BagProductModelToEntity @Inject constructor() {

    fun convert(model: Product) = BagProductEntity(
        id = model.id,
        name = model.name,
        quantity = model.quantity,
        price = model.price,
        description = model.description,
        type = model.type,
        energyValue = model.energyValue,
        weight = model.weight,
        photoName = model.photoName,
        photoUrl = model.photoUrl
    )

}