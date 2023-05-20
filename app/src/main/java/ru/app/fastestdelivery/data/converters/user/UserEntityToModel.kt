package ru.app.fastestdelivery.data.converters.user

import ru.app.fastestdelivery.data.models.database.UserEntity
import ru.app.fastestdelivery.domain.models.User
import javax.inject.Inject

class UserEntityToModel @Inject constructor() {

    fun convert(entity: UserEntity) = User(
        id = entity.id,
        name = entity.name,
        token = entity.token
    )

}