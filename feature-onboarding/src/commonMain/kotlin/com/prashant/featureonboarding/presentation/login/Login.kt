package com.prashant.featureonboarding.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.prashant.core.domain.models.network.LoginRequestBody
import com.prashant.core.ui.uicomponents.CoreButton
import com.prashant.core.utils.LogUtil.logcat
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Login(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel(),
    navigateHome: () -> Unit,
) {
    val loginResponse by loginViewModel.loginResponse.collectAsState()
    LaunchedEffect(Unit) {
        logcat(keyName = "Login Screen", value = "$loginResponse")
    }
    LoginContainer(modifier = modifier) {
        loginViewModel.loginWithEmail(LoginRequestBody("email", "password")) {
            logcat("Login", "$loginResponse")
            navigateHome()
        }
    }
}

@Composable
private fun LoginContainer(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Login Screen")
        CoreButton(label = "Go Home", onClick = onLogin)
    }
}
