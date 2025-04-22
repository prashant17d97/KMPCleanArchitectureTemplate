package app.debugdesk.coredata.repository

import com.prashant.core.domain.models.AppSettingState
import com.prashant.core.domain.models.enums.Language
import com.prashant.core.domain.models.enums.ThemeMode
import com.prashant.core.domain.repository.local.AppSettingRepository
import com.prashant.localdatasource.preference.AppSettingsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppSettingRepositoryImpl(private val appSettingManager: AppSettingsManager) :
    AppSettingRepository {
    private val appSettingStateFlow = MutableStateFlow(AppSettingState())
    override val appSettingState: StateFlow<AppSettingState>
        get() = appSettingStateFlow

    override fun getAppSettingState() {
        appSettingStateFlow.value =
            with(appSettingManager) {
                AppSettingState(
                    themeMode = getAppTheme(),
                    language = getAppLanguage(),
                    firstLaunch = isFirstLaunch(),
                    isOnBoardingCompleted = isOnBoardingCompleted(),
                    onBoardingPage = getOnBoardingPage(),
                    notificationEnabled = isNotificationEnabled(),
                    isBiometricEnabled = isBiometricEnabled(),
                    isUserSessionExpired = isUserSessionExpired(),
                    useDynamicColor = isDynamicColorEnabled(),
                )
            }
    }

    override fun updateThemeMode(themeMode: ThemeMode) {
        appSettingManager.saveAppTheme(themeMode.name)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(themeMode = themeMode)
    }

    override fun updateUserSession(isExpired: Boolean) {
        appSettingManager.saveUserSessionExpired(isExpired)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(isUserSessionExpired = isExpired)
    }

    override fun updateLanguage(language: Language) {
        appSettingManager.saveAppLanguage(language.name)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(language = language)
    }

    override fun updateFirstLaunch(firstLaunch: Boolean) {
        appSettingManager.saveFirstLaunch(firstLaunch)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(firstLaunch = firstLaunch)
    }

    override fun updateOnBoardingCompleted(isOnBoardingCompleted: Boolean) {
        appSettingManager.saveOnBoardingCompleted(isOnBoardingCompleted)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(isOnBoardingCompleted = isOnBoardingCompleted)
    }

    override fun updateOnBoardingPage(onBoardingPage: Int) {
        appSettingManager.saveOnBoardingPage(onBoardingPage)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(onBoardingPage = onBoardingPage)
    }

    override fun updateNotificationEnabled(notificationEnabled: Boolean) {
        appSettingManager.saveNotificationEnabled(notificationEnabled)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(notificationEnabled = notificationEnabled)
    }

    override fun updateBiometricEnabled(isBiometricEnabled: Boolean) {
        appSettingManager.saveBiometricEnabled(isBiometricEnabled)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(isBiometricEnabled = isBiometricEnabled)
    }

    override fun updateUserSessionExpired(isUserSessionExpired: Boolean) {
        appSettingManager.saveUserSessionExpired(isUserSessionExpired)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(isUserSessionExpired = isUserSessionExpired)
    }

    override fun updateDynamicColorEnabled(useDynamicColor: Boolean) {
        appSettingManager.saveDynamicColorEnabled(useDynamicColor)
        appSettingStateFlow.value = appSettingStateFlow.value.copy(useDynamicColor = useDynamicColor)
    }

    override fun clearAppSettings() {
        appSettingManager.clearAppSettings()
        appSettingStateFlow.value = AppSettingState()
    }
}
