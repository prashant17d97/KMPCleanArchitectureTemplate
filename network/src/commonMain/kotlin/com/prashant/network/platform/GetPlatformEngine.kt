package com.prashant.network.platform

import io.ktor.client.engine.HttpClientEngineFactory

expect fun getPlatformEngine(): HttpClientEngineFactory<*>
