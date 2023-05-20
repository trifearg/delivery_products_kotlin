package ru.app.fastestdelivery.data.converters.user

import ru.app.fastestdelivery.data.models.database.UserEntity
import ru.app.fastestdelivery.data.models.network.LoginResponseModel
import ru.app.fastestdelivery.data.models.network.RegisterResponseModel
import javax.inject.Inject

class UserResponseToEntity @Inject constructor() {

    fun convert(response: LoginResponseModel) = UserEntity(
        id = response.userId,
        name = response.name,
        token = response.token
    )

    fun convert(response: RegisterResponseModel) = UserEntity(
        id = response.userId,
        name = response.name,
        token = response.token
    )

}