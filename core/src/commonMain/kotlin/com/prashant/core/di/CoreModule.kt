package com.prashant.core.di

import com.plusmobileapps.konnectivity.Konnectivity
import com.prashant.core.utils.InternetConnectivityManager
import org.koin.dsl.module

val coreModule =
    module {
        single { InternetConnectivityManager() }
        single<Konnectivity> { Konnectivity() }
    }
