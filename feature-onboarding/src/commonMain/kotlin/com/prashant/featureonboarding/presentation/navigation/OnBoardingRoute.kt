package com.prashant.featureonboarding.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface OnBoardingRoute {
    @Serializable
    data object Intro : OnBoardingRoute

    @Serializable
    data object Login : OnBoardingRoute
}
