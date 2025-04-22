package com.prashant.core.domain.repository.local

import com.prashant.core.domain.models.AppSettingState
import com.prashant.core.domain.models.enums.Language
import com.prashant.core.domain.models.enums.ThemeMode
import kotlinx.coroutines.flow.StateFlow

interface AppSettingRepository {
    /**
     * AppSettingRepository is responsible for managing the app settings.
     * It provides a way to get and update the app settings.
     */
    val appSettingState: StateFlow<AppSettingState>

    fun getAppSettingState()

    fun updateThemeMode(themeMode: ThemeMode)

    fun updateUserSession(isExpired: Boolean)

    fun updateLanguage(language: Language)

    fun updateFirstLaunch(firstLaunch: Boolean)

    fun updateOnBoardingCompleted(isOnBoardingCompleted: Boolean)

    fun updateOnBoardingPage(onBoardingPage: Int)

    fun updateNotificationEnabled(notificationEnabled: Boolean)

    fun updateBiometricEnabled(isBiometricEnabled: Boolean)

    fun updateUserSessionExpired(isUserSessionExpired: Boolean)

    fun updateDynamicColorEnabled(useDynamicColor: Boolean)

    fun clearAppSettings()
}
