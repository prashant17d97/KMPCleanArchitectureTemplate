package com.prashant.localdatasource.di

import com.liftric.kvault.KVault
import org.koin.core.module.Module
import org.koin.dsl.module

actual val preferenceStoreModule: Module
    get() =
        module {
            single<KVault> { KVault(context = get(), fileName = "secret_shared_prefs") }
        }
