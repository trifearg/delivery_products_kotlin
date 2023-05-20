package ru.app.fastestdelivery.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.app.fastestdelivery.domain.AuthUseCase
import ru.app.fastestdelivery.presentation.screens.Screens
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val router: Router
): ViewModel() {

    fun continueButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = authUseCase.getUser()
            if (user == null) {
                router.navigateTo(Screens.loginScreen())
            } else {
                router.newRootScreen(Screens.mainScreen())
            }
        }
    }

}