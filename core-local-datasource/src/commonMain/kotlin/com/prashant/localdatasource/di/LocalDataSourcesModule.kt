package com.prashant.localdatasource.di

import com.prashant.core.domain.repository.local.PreferenceStore
import com.prashant.core.domain.repository.local.TokenManager
import com.prashant.localdatasource.preference.AppSettingsManager
import com.prashant.localdatasource.preference.SecurePreferenceManager
import com.prashant.localdatasource.preference.TokenManagerImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val localDataSourcesModule =
    module {
        includes(preferenceStoreModule)
        single<PreferenceStore> { SecurePreferenceManager(kVault = get()) }
        single<TokenManager> { TokenManagerImpl(preferenceStore = get()) }
        single<AppSettingsManager> { AppSettingsManager(preferenceStore = get()) }
    }

expect val preferenceStoreModule: Module
