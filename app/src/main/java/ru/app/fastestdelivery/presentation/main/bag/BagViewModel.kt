package ru.app.fastestdelivery.presentation.main.bag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.app.fastestdelivery.domain.BagProductsUseCase
import ru.app.fastestdelivery.domain.models.Product
import ru.app.fastestdelivery.presentation.main.bag.models.State
import ru.app.fastestdelivery.presentation.screens.Screens
import javax.inject.Inject

@HiltViewModel
class BagViewModel @Inject constructor(
    private val bagProductsUseCase: BagProductsUseCase,
    private val router: Router
) : ViewModel() {

    private val initialState = State(items = emptyList())
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        observeBagProducts()
    }

    fun onBuyButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            bagProductsUseCase.deleteBagProducts()
        }
        router.navigateTo(Screens.paymentResultScreen())
    }

    private fun observeBagProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            bagProductsUseCase.getBagProductsFlow().collect { products ->
                _state.update { it.copy(items = products) }
            }
        }
    }

    fun onProductPlusClicked(product: Product) {
        val newProduct = product.copy(quantity = product.quantity + 1)
        viewModelScope.launch(Dispatchers.IO) {
            bagProductsUseCase.insertBagProduct(newProduct)
        }
    }

    fun onProductMinusClicked(product: Product) {
        if (product.quantity > 0) {
            val newProduct = product.copy(quantity = product.quantity - 1)
            viewModelScope.launch(Dispatchers.IO) {
                bagProductsUseCase.insertBagProduct(newProduct)
            }
        }
    }

    fun onProductDeleteClicked(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            bagProductsUseCase.deleteBagProduct(product.id)
        }
    }

}