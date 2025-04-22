package com.prashant.featureonboarding.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.prashant.featureonboarding.presentation.intro.Intro
import com.prashant.featureonboarding.presentation.login.Login

fun NavGraphBuilder.onboardingNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navigateHome: () -> Unit = {},
) {
    composable<OnBoardingRoute.Intro> {
        Intro(
            navigateToLogin = {
                navController.navigate(route = OnBoardingRoute.Login) {
                    popUpTo(
                        route = OnBoardingRoute.Intro,
                        popUpToBuilder = {
                            inclusive = true
                        },
                    )
                }
            },
            modifier = modifier,
        )
    }

    composable<OnBoardingRoute.Login> {
        Login(modifier = modifier, navigateHome = navigateHome)
    }
}
