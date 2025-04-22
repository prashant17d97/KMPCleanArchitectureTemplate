package com.prashant.localdatasource.preference

import com.prashant.core.domain.models.enums.Language
import com.prashant.core.domain.models.enums.ThemeMode
import com.prashant.core.domain.repository.local.PreferenceStore

class AppSettingsManager(private val preferenceStore: PreferenceStore) {
    companion object {
        private const val APP_THEME = "app_theme"
        private const val APP_LANGUAGE = "app_language"
        private const val FIRST_LAUNCH = "first_launch"
        private const val ON_BOARDING_PAGE = "on_boarding_page"
        private const val NOTIFICATION_ENABLED = "is_notification_enabled"
        private const val BIOMETRIC_ENABLED = "is_biometric_enabled"
        private const val IS_ON_BOARDING_COMPLETED = "is_on_boarding_completed"
        private const val IS_USER_SESSION_EXPIRED = "is_user_session_expired"
        private const val USE_DYNAMIC_COLOR = "use_dynamic_color"
    }

    fun saveAppTheme(theme: String) {
        preferenceStore.putString(APP_THEME, theme)
    }

    fun getAppTheme(): ThemeMode {
        return preferenceStore.getString(APP_THEME, ThemeMode.SYSTEM.name).toThemeMode()
    }

    fun saveAppLanguage(language: String) {
        preferenceStore.putString(APP_LANGUAGE, language)
    }

    fun getAppLanguage(): Language {
        return preferenceStore.getString(APP_LANGUAGE, Language.ENGLISH.name).toLanguage()
    }

    fun saveFirstLaunch(isFirstLaunch: Boolean) {
        preferenceStore.putBoolean(FIRST_LAUNCH, isFirstLaunch)
    }

    fun isFirstLaunch(): Boolean {
        return preferenceStore.getBoolean(FIRST_LAUNCH, true)
    }

    fun saveOnBoardingPage(onBoardingPage: Int) {
        preferenceStore.putInt(ON_BOARDING_PAGE, onBoardingPage)
    }

    fun getOnBoardingPage(): Int {
        return preferenceStore.getInt(ON_BOARDING_PAGE, 0)
    }

    fun saveNotificationEnabled(isEnabled: Boolean) {
        preferenceStore.putBoolean(NOTIFICATION_ENABLED, isEnabled)
    }

    fun isNotificationEnabled(): Boolean {
        return preferenceStore.getBoolean(NOTIFICATION_ENABLED, true)
    }

    fun saveBiometricEnabled(isEnabled: Boolean) {
        preferenceStore.putBoolean(BIOMETRIC_ENABLED, isEnabled)
    }

    fun isBiometricEnabled(): Boolean {
        return preferenceStore.getBoolean(BIOMETRIC_ENABLED, false)
    }

    fun saveOnBoardingCompleted(isCompleted: Boolean) {
        preferenceStore.putBoolean(IS_ON_BOARDING_COMPLETED, isCompleted)
    }

    fun isOnBoardingCompleted(): Boolean {
        return preferenceStore.getBoolean(IS_ON_BOARDING_COMPLETED, false)
    }

    fun isUserSessionExpired(): Boolean {
        return preferenceStore.getBoolean(IS_USER_SESSION_EXPIRED, false)
    }

    fun saveUserSessionExpired(isExpired: Boolean) {
        preferenceStore.putBoolean(IS_USER_SESSION_EXPIRED, isExpired)
    }

    fun saveDynamicColorEnabled(useDynamicColor: Boolean) {
        preferenceStore.putBoolean(USE_DYNAMIC_COLOR, useDynamicColor)
    }

    fun isDynamicColorEnabled(): Boolean {
        return preferenceStore.getBoolean(USE_DYNAMIC_COLOR, false)
    }

    fun clearAppSettings() {
        with(preferenceStore) {
            remove(APP_THEME)
            remove(APP_LANGUAGE)
            remove(FIRST_LAUNCH)
            remove(ON_BOARDING_PAGE)
            remove(NOTIFICATION_ENABLED)
            remove(BIOMETRIC_ENABLED)
            remove(IS_ON_BOARDING_COMPLETED)
            remove(IS_USER_SESSION_EXPIRED)
            remove(USE_DYNAMIC_COLOR)
        }
    }

    private fun String?.toLanguage(): Language {
        return Language.entries.firstOrNull { it.name == this } ?: Language.ENGLISH
    }

    private fun String?.toThemeMode(): ThemeMode {
        return ThemeMode.entries.firstOrNull { it.name == this } ?: ThemeMode.SYSTEM
    }
}
