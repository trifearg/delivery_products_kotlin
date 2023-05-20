package ru.app.fastestdelivery.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import ru.app.fastestdelivery.data.api.AppApi
import ru.app.fastestdelivery.data.converters.product.BagProductEntityToModel
import ru.app.fastestdelivery.data.converters.product.BagProductModelToEntity
import ru.app.fastestdelivery.data.converters.product.ProductEntityToModel
import ru.app.fastestdelivery.data.converters.product.ProductResponseToEntity
import ru.app.fastestdelivery.data.converters.user.UserEntityToModel
import ru.app.fastestdelivery.data.converters.user.UserResponseToEntity
import ru.app.fastestdelivery.data.errors.ErrorResponse
import ru.app.fastestdelivery.data.errors.ErrorsConverter
import ru.app.fastestdelivery.data.models.network.CreateOrderRequestModel
import ru.app.fastestdelivery.data.models.network.CreateOrderResponseModel
import ru.app.fastestdelivery.data.models.network.LoginRequestModel
import ru.app.fastestdelivery.data.models.network.RegisterRequestModel
import ru.app.fastestdelivery.data.room.BagProductDao
import ru.app.fastestdelivery.data.room.ProductDao
import ru.app.fastestdelivery.data.room.UserDao
import ru.app.fastestdelivery.domain.models.Product
import ru.app.fastestdelivery.domain.models.User
import ru.app.fastestdelivery.util.MessageException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: AppApi,
    private val userDao: UserDao,
    private val productDao: ProductDao,
    private val bagProductDao: BagProductDao,
    private val errorsConverter: ErrorsConverter,
    private val userResponseToEntity: UserResponseToEntity,
    private val userEntityToModel: UserEntityToModel,
    private val productResponseToEntity: ProductResponseToEntity,
    private val productEntityToModel: ProductEntityToModel,
    private val bagProductModelToEntity: BagProductModelToEntity,
    private val bagProductEntityToModel: BagProductEntityToModel,
) {

    suspend fun login(email: String, password: String) {
        val params = LoginRequestModel(email = email, password = password)
        val response = api.login(params = params)
        if (response.isSuccessful) {
            userDao.insertUser(userResponseToEntity.convert(response.body()!!))
        } else {
            throw MessageException(message = errorMessage(response))
        }
    }

    suspend fun register(name: String, email: String, password: String) {
        val params = RegisterRequestModel(
                name = name,
                email = email,
                password = password
        )
        val response = api.register(params = params)
        if (response.isSuccessful) {
            userDao.insertUser(userResponseToEntity.convert(response.body()!!))
        } else {
            throw MessageException(message = errorMessage(response))
        }
    }


    suspend fun logout() {
        userDao.clearUser()
    }

    suspend fun getUser(): User? = userDao.getUsers().firstOrNull()?.let(userEntityToModel::convert)

    suspend fun getAllProducts(): List<Product> {
        val response = api.allProducts()
        if (response.isSuccessful) {
            val entities = response.body()!!.products.map(productResponseToEntity::convert)
            productDao.insertProducts(entities)
            return entities.map(productEntityToModel::convert)
        } else {
            throw MessageException(message = errorMessage(response))
        }
    }

    suspend fun getBagProductsFlow(): Flow<List<Product>> {
        bagProductDao.deleteEmptyBagProducts()
        return bagProductDao.getBagProductsFlow().map { entities ->
            entities.map(bagProductEntityToModel::convert)
        }
    }

    suspend fun insertBagProduct(product: Product) {
        bagProductDao.insertBagProduct(bagProductModelToEntity.convert(product))
    }

    suspend fun deleteBagProduct(id: Int) {
        bagProductDao.deleteBagProduct(id)
    }

    suspend fun deleteBagProducts() {
        bagProductDao.deleteProductsFromBag()
    }

    suspend fun createOrder(customerId: Int, price: Double, status: String): Response<CreateOrderResponseModel> {
        val params = CreateOrderRequestModel(
                customerId = customerId,
                price = price,
                status = status
        )
        return api.createOrder(params = params)
    }

    suspend fun getOrders(customerId: Int) = api.getOrders(customerId = customerId)

    private fun errorMessage(response: Response<*>): String {
        return errorsConverter.convert(
                jsonString = response.errorBody()?.string().orEmpty(),
                type = ErrorResponse::class.java
            )
            ?.message
            .orEmpty()
    }

}