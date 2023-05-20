package ru.app.fastestdelivery.presentation.auth.login

import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.app.fastestdelivery.R
import ru.app.fastestdelivery.domain.AuthUseCase
import ru.app.fastestdelivery.presentation.screens.Screens
import javax.inject.Inject
import ru.app.fastestdelivery.presentation.auth.login.models.State
import ru.app.fastestdelivery.util.MessageException
import ru.app.fastestdelivery.util.ui.UiText
import ru.app.fastestdelivery.util.ui.inputState.InputState
import java.lang.Exception

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val router: Router,
): ViewModel() {

    private val initialState = State(
        email = InputState.Data(""),
        password = InputState.Data("")
    )
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _errorEvent: MutableSharedFlow<UiText> = MutableSharedFlow()
    val errorEvent: SharedFlow<UiText> = _errorEvent.asSharedFlow()

    fun onContinueButtonClicked() {
        val email = (_state.value.email as? InputState.Data)?.value.orEmpty()
        if (email.isBlank()) {
            _state.update { it.copy(email = InputState.Error.EmptyInput) }
        }

        val password = (_state.value.password as? InputState.Data)?.value.orEmpty()
        if (password.isBlank()) {
            _state.update { it.copy(password = InputState.Error.EmptyInput) }
        }

        if (_state.value.email.isData() && _state.value.password.isData()) {
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                authUseCase.login(email = email, password = password)
                withContext(Dispatchers.Main) { router.newRootScreen(Screens.mainScreen()) }
            } catch (e: Exception) {
                if (e is MessageException) {
                    _errorEvent.emit(UiText.DynamicString(e.message))
                } else {
                    _errorEvent.emit(UiText.StringResource(R.string.global_unknown_error))
                }
            }
        }
    }

    fun onEmailUpdated(email: Editable?) {
        _state.update { it.copy(email = InputState.Data(email.toString())) }
    }

    fun onPasswordUpdated(password: Editable?) {
        _state.update { it.copy(password = InputState.Data(password.toString())) }
    }

}