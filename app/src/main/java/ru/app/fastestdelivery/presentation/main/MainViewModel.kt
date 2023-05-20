package ru.app.fastestdelivery.presentation.main

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
import ru.app.fastestdelivery.R
import ru.app.fastestdelivery.presentation.main.models.State
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {

    private val initialState = State(selectedTabId = R.id.menu_home, myLocationAddress = "")
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    fun onTabClicked(tabId: Int) {
        _state.update { it.copy(selectedTabId = tabId) }
    }

    fun setMyAddress(address: String) {
        _state.update { it.copy(myLocationAddress = address) }
    }

}