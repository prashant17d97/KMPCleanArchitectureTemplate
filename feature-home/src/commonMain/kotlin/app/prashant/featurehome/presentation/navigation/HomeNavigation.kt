package app.prashant.featurehome.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeNavigation {
    @Serializable
    data object Home : HomeNavigation

    @Serializable
    data object Settings : HomeNavigation

    @Serializable
    data object About : HomeNavigation

    @Serializable
    data object Feedback : HomeNavigation

    @Serializable
    data object Share : HomeNavigation

    @Serializable
    data object Rate : HomeNavigation

    @Serializable
    data object PrivacyPolicy : HomeNavigation

    @Serializable
    data object TermsOfService : HomeNavigation
}
