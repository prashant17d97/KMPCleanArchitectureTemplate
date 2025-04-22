package com.prashant.network.platform

import io.ktor.client.engine.HttpClientEngineFactory

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object PlatformUtils {
    fun isDebugBuild(): Boolean

    fun getPlatformEngine(): HttpClientEngineFactory<*>
}
