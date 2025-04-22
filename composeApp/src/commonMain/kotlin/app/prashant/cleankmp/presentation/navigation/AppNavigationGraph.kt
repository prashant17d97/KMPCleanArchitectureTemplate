package app.prashant.cleankmp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import app.prashant.featurehome.presentation.navigation.HomeNavigation
import app.prashant.featurehome.presentation.navigation.homeNavigationGraph
import com.prashant.featureonboarding.presentation.navigation.OnBoardingRoute
import com.prashant.featureonboarding.presentation.navigation.onboardingNavigation

@Composable
fun AppNavigationGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: Any = OnBoardingRoute.Intro,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        onboardingNavigation(
            navController = navHostController,
            navigateHome = {
                navHostController.navigate(route = HomeNavigation.Home) {
                    popUpTo(
                        route = OnBoardingRoute.Login,
                        popUpToBuilder = {
                            inclusive = true
                        },
                    )
                }
            },
        )

        homeNavigationGraph {}
    }
}
