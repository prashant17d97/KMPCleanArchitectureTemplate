package com.prashant.featureonboarding.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashant.core.domain.models.network.ApiResult
import com.prashant.core.domain.models.network.LoginRequestBody
import com.prashant.featureonboarding.usecases.LoginWithEmailUseCase
import com.prashant.uialert.api.AlertDispatcher
import com.prashant.uialert.event.AlertType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class LoginViewModel(
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val alertDispatcher: AlertDispatcher,
) : ViewModel(), KoinComponent {
    private val loginResponseFlow: MutableStateFlow<Unit> = MutableStateFlow(Unit)
    val loginResponse: StateFlow<Unit> = loginResponseFlow

    fun loginWithEmail(
        loginRequestBody: LoginRequestBody,
        onLoginSuccess: () -> Unit,
    ) {
        alertDispatcher.showCircularProgressIndicator(message = "Logging in please wait...")
        viewModelScope.launch {
            val (message, type) =
                when (val response = loginWithEmailUseCase(loginRequestBody)) {
                    is ApiResult.Success<*> -> null to AlertType.SUCCESS
                    ApiResult.Timeout -> "Request timed out" to AlertType.INFO
                    ApiResult.NoInternet -> "You are not connected to the internet" to AlertType.ERROR
                    is ApiResult.Error -> response.message to AlertType.ERROR
                    is ApiResult.Unknown -> (response.message ?: "An unknown error occurred") to AlertType.WARNING
                    else -> {
                        "An unknown error occurred" to AlertType.WARNING
                    }
                }
            alertDispatcher.hide()
            onLoginSuccess()
            alertDispatcher.showSnackBar(message = message, alertType = type)
        }
    }
}
