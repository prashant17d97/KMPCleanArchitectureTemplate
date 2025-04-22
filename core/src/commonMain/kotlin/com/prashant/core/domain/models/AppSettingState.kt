package com.prashant.core.domain.models

import com.prashant.core.domain.models.enums.Language
import com.prashant.core.domain.models.enums.ThemeMode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppSettingState(
    @SerialName("theme_mode")
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    @SerialName("is_dark_theme")
    val language: Language = Language.ENGLISH,
    @SerialName("first_launch")
    val firstLaunch: Boolean = true,
    @SerialName("is_on_boarding_completed")
    val isOnBoardingCompleted: Boolean = false,
    @SerialName("on_boarding_page")
    val onBoardingPage: Int = 0,
    @SerialName("is_notification_enabled")
    val notificationEnabled: Boolean = true,
    @SerialName("is_biometric_enabled")
    val isBiometricEnabled: Boolean = false,
    @SerialName("is_user_session_expired")
    val isUserSessionExpired: Boolean = false,
    @SerialName("use_dynamic_color")
    val useDynamicColor: Boolean = false,
)
