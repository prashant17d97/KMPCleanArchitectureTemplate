package com.prashant.featureonboarding.presentation.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.prashant.core.ui.uicomponents.CoreButton
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Intro(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    introViewModel: IntroViewModel = koinViewModel(),
) {
    val currentPage by introViewModel.currentPage.collectAsState()
    IntroScreen(
        modifier = modifier,
        currentPage = currentPage,
        onSkip = {
            introViewModel.updateFirstLaunch(true)
            navigateToLogin()
        },
        onNext = { },
        onFinish = {
            introViewModel.updateFirstLaunch(true)
            navigateToLogin()
        },
        onLogin = {
            navigateToLogin()
        },
    )
}

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    currentPage: Int,
    onSkip: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onLogin: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Intro Screen $currentPage")
        CoreButton(label = "Skip", onClick = onLogin)
    }
}
