package app.prashant.featurehome.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.prashant.featurehome.presentation.home.Home

fun NavGraphBuilder.homeNavigationGraph(
    modifier: Modifier = Modifier,
    navigateToOnboarding: () -> Unit = {},
) {
    composable<HomeNavigation.Home> {
        Home(
            modifier = modifier,
            onNavigate = navigateToOnboarding,
        )
    }
}
