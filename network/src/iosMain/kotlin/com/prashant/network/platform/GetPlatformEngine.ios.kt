package com.prashant.network.platform

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun getPlatformEngine(): HttpClientEngineFactory<*> = Darwin
