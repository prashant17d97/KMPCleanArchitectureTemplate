package com.prashant.coredata.di

import app.debugdesk.coredata.repository.AppSettingRepositoryImpl
import com.prashant.core.domain.repository.local.AppSettingRepository
import com.prashant.core.domain.repository.remote.AuthRepository
import com.prashant.coredata.repository.AuthRepositoryImpl
import com.prashant.localdatasource.di.localDataSourcesModule
import com.prashant.network.di.networkModule
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.includesDataModule() {
    modules(localDataSourcesModule)
    modules(networkModule)
    modules(dataModule)
}

private val dataModule =
    module {
        // Repository
        single<AuthRepository> { AuthRepositoryImpl(baseApiService = get()) }
        single<AppSettingRepository> { AppSettingRepositoryImpl(appSettingManager = get()) }
    }
