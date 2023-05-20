package ru.app.fastestdelivery.presentation.auth

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.app.fastestdelivery.presentation.auth.login.LoginFragment
import ru.app.fastestdelivery.presentation.auth.models.AuthTab
import ru.app.fastestdelivery.presentation.auth.models.State
import ru.app.fastestdelivery.presentation.auth.register.RegisterFragment
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {

    private val initialState = State(selectedTab = AuthTab.LOGIN)
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    fun onBackClicked() {
        router.exit()
    }

    fun onTabClicked(tab: AuthTab) {
        _state.update { it.copy(selectedTab = tab) }
    }

}