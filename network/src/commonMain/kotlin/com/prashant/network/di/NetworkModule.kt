package com.prashant.network.di

import com.prashant.network.BaseApiService
import com.prashant.network.KtorClientProvider
import io.ktor.http.Url
import org.koin.dsl.module

val networkModule =
    module {
        single {
            KtorClientProvider.createKtorClient(tokenManager = get(), baseUrl = Url("https://prashant.up.railway.app/"))
        }
        single {
            BaseApiService(
                client = get(),
                internetManager = get(),
            )
        }
    }
