package app.prashant.cleankmp.presentation

import androidx.lifecycle.ViewModel
import com.prashant.core.domain.repository.local.AppSettingRepository
import org.koin.core.component.KoinComponent

class MainViewModel(private val appSettingRepository: AppSettingRepository) : ViewModel(), KoinComponent {
    val appSettingState = appSettingRepository.appSettingState

    init {
        appSettingRepository.getAppSettingState()
    }
}
