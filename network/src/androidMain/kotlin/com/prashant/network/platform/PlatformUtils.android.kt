
package com.prashant.network.platform

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object PlatformUtils {
    actual fun isDebugBuild(): Boolean = true

    actual fun getPlatformEngine(): HttpClientEngineFactory<*> = OkHttp
}
