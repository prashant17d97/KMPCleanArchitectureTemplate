package com.prashant.uialert.di

import com.prashant.uialert.api.AlertDispatcher
import com.prashant.uialert.impl.AlertDispatcherImpl
import org.koin.dsl.module

val alertModule =
    module {
        single<AlertDispatcher> {
            AlertDispatcherImpl()
        }
    }
