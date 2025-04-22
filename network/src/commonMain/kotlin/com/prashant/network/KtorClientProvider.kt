package com.prashant.network

import com.prashant.core.domain.repository.local.TokenManager
import com.prashant.network.platform.PlatformUtils.isDebugBuild
import com.prashant.network.platform.getPlatformEngine
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.Url
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientProvider {
    /**
     * Creates a Ktor HttpClient instance with OkHttp engine and default configurations.
     *
     * @param tokenManager authentication token provider to be included in the headers.
     * @return Configured HttpClient instance.
     */
    fun createKtorClient(
        tokenManager: TokenManager,
        baseUrl: Url,
    ): HttpClient {
        return HttpClient(getPlatformEngine()) {
            expectSuccess = false

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = false
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }

            install(Logging) {
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            print("KtorClient, message")
                        }
                    }
                level = if (isDebugBuild()) LogLevel.INFO else LogLevel.NONE
            }

            install(DefaultRequest) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)

                url {
                    protocol = baseUrl.protocolOrNull?.let {
                        URLProtocol.createOrDefault(it.name)
                    } ?: URLProtocol.HTTPS
                    host = baseUrl.host
                }

                tokenManager.getToken()?.let {
                    header(HttpHeaders.Authorization, it)
                }

                header(HttpHeaders.Accept, "application/json")
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 30000
                connectTimeoutMillis = 30000
                socketTimeoutMillis = 30000
            }
        }
    }
}
