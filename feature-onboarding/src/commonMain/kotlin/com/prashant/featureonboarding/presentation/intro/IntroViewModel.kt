package com.prashant.featureonboarding.presentation.intro

import androidx.lifecycle.ViewModel
import com.prashant.core.domain.repository.local.AppSettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class IntroViewModel(
    private val appSettingRepository: AppSettingRepository,
) : ViewModel(), KoinComponent {
    private val currentPageFlow = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = currentPageFlow.asStateFlow()

    fun updateFirstLaunch(firstLaunch: Boolean) {
        appSettingRepository.updateFirstLaunch(firstLaunch)
    }
}
