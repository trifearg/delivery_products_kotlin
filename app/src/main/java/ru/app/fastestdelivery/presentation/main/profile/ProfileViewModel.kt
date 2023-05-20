package ru.app.fastestdelivery.presentation.main.profile

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
import ru.app.fastestdelivery.domain.AuthUseCase
import ru.app.fastestdelivery.presentation.main.profile.models.State
import ru.app.fastestdelivery.presentation.screens.Screens
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val router: Router
) : ViewModel() {

    private val initialState = State(userName = "")
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        getUserName()
    }
    fun onLogoutClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.logout()
            router.newRootScreen(Screens.splashScreen())
        }
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = authUseCase.getUser()
            if (user != null) {
                _state.update { it.copy(userName = user.name) }
            }
        }
    }

}