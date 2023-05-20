package ru.app.fastestdelivery.presentation.main.home

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
import ru.app.fastestdelivery.domain.HomeProductsUseCase
import ru.app.fastestdelivery.domain.models.Product
import ru.app.fastestdelivery.presentation.main.home.models.State
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeProductsUseCase: HomeProductsUseCase,
    private val router: Router
) : ViewModel() {

    private val initialState = State(items = emptyList())
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        observeHomeProducts()
    }
    fun onProductClicked(product: Product) {
        val newProduct = product.copy(quantity = product.quantity + 1)
        viewModelScope.launch(Dispatchers.IO) {
            homeProductsUseCase.insertBagProduct(newProduct)
        }
    }

    private fun observeHomeProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            homeProductsUseCase.getHomeProductsFlow().let { products ->
                _state.update { it.copy(items = products) }
            }
        }
    }

}