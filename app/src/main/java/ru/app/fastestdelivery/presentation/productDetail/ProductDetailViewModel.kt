package ru.app.fastestdelivery.presentation.productDetail

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.app.fastestdelivery.presentation.productDetail.models.State
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {

    private val initialState = State(productId = null)
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    fun onBackClicked() {
        router.exit()
    }

    fun init(productId: Int) {
        // TODO (загрузить продукт из БД)
    }

}