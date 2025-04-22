package app.prashant.cleankmp.presentation

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import app.prashant.cleankmp.presentation.navigation.AppNavigationGraph
import com.prashant.core.ui.theme.AppTheme
import com.prashant.core.ui.uicomponents.AppScaffold
import com.prashant.core.utils.CoreUtils.darkMode
import com.prashant.core.utils.InternetConnectivityManager
import com.prashant.uialert.api.AlertDispatcher
import com.prashant.uialert.event.AlertType
import com.prashant.uialert.host.AlertAndIndicator
import com.prashant.uialert.host.SnackbarHost
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val mainViewModel: MainViewModel = koinViewModel()
    val alertDispatcher: AlertDispatcher = koinInject()

    val internetManager: InternetConnectivityManager = koinInject()
    val snackbarHost by alertDispatcher.snackBarHost.collectAsState(initial = null)
    val dialog by alertDispatcher.dialog.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    val appSettingState by mainViewModel.appSettingState.collectAsState()

    LaunchedEffect(Unit) {
        internetManager.observeSmartConnectivity().collect { isConnected ->
            val (message, type) =
                if (isConnected) {
                    "Back online" to AlertType.INFO
                } else {
                    "No active internet connection" to AlertType.ERROR
                }
            alertDispatcher.showSnackBar(
                message = message,
                alertType = type,
            )
        }
    }

    AppTheme(
        darkTheme = appSettingState.themeMode.darkMode,
        dynamicColor = appSettingState.useDynamicColor,
    ) {
        AppScaffold(
            modifier = Modifier.imePadding(),
            alertDialog = {
                AlertAndIndicator(
                    alertDialog = dialog,
                )
            },
            snackbarHost = {
                SnackbarHost(
                    snackBarHost = snackbarHost,
                    snackbarHostState = snackbarHostState,
                )
            },
        ) {
            AppNavigationGraph(
                modifier = Modifier.padding(it),
                navHostController = navController,
            )
        }
    }
}
