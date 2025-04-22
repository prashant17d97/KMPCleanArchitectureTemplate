package com.prashant.network.platform

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual object PlatformUtils {
    actual fun isDebugBuild(): Boolean {
        TODO("Not yet implemented")
    }

    actual fun getPlatformEngine(): HttpClientEngineFactory<*> = Darwin
}
