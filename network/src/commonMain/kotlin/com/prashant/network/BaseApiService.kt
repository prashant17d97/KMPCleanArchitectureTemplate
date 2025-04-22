package com.prashant.network

import com.prashant.core.domain.models.network.ApiResult
import com.prashant.core.utils.CoroutineUtils.backgroundScope
import com.prashant.core.utils.InternetConnectivityManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.allStatusCodes
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import kotlinx.coroutines.CancellationException

/**
 * A generic base class for handling HTTP requests using Ktor.
 *
 * This class standardizes common HTTP operations (`GET`, `POST`, `PUT`, `DELETE`)
 * and wraps results in [ApiResult] to handle success and error states uniformly.
 *
 * @property client The configured [HttpClient] used to perform network requests.
 */
class BaseApiService(
    val client: HttpClient,
    val internetManager: InternetConnectivityManager,
) {
    /**
     * Executes a GET request with path and query parameters.
     *
     * @param Response The expected response type.
     * @param pathSegment Path segments as a single string, e.g., "users/123".
     * @param queryParams Optional query parameters to be appended to the URL.
     * @param headers Optional headers to include in the request.
     */
    suspend inline fun <reified Response> get(
        pathSegment: String,
        queryParams: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
    ): ApiResult<Response> =
        safeCall {
            client.get {
                contentType(ContentType.Application.Json)
                headers.forEach { (key, value) -> header(key, value) }

                url {
                    appendPathSegments(pathSegment.toCleanPathSegments())
                    queryParams.forEach { (key, value) -> parameters.append(key, value) }
                }
            }.body()
        }

    /**
     * Executes a POST request with path, optional body and query parameters.
     *
     * @param Response The expected response type.
     * @param Request The type of the request body.
     * @param pathSegment Path segments as a single string.
     * @param body Request body to send.
     * @param queryParams Optional query parameters.
     * @param headers Optional headers.
     */
    suspend inline fun <reified Response, reified Request : Any> post(
        pathSegment: String,
        body: Request,
        queryParams: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
    ): ApiResult<Response> =
        safeCall {
            client.post {
                contentType(ContentType.Application.Json)
                setBody(body)
                headers.forEach { (key, value) -> header(key, value) }

                url {
                    appendPathSegments(pathSegment.toCleanPathSegments())
                    queryParams.forEach { (key, value) -> parameters.append(key, value) }
                }
            }.body()
        }

    /**
     * Executes a PUT request with path, optional body and query parameters.
     *
     * @param Response The expected response type.
     * @param Request The type of the request body.
     * @param pathSegment Path segments as a single string.
     * @param body Request body to send.
     * @param queryParams Optional query parameters.
     * @param headers Optional headers.
     */
    suspend inline fun <reified Response, reified Request : Any> put(
        pathSegment: String,
        body: Request,
        queryParams: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
    ): ApiResult<Response> =
        safeCall {
            client.put {
                contentType(ContentType.Application.Json)
                setBody(body)
                headers.forEach { (key, value) -> header(key, value) }

                url {
                    appendPathSegments(pathSegment.toCleanPathSegments())
                    queryParams.forEach { (key, value) -> parameters.append(key, value) }
                }
            }.body()
        }

    /**
     * Executes a DELETE request with path and optional query parameters.
     *
     * @param Response The expected response type.
     * @param pathSegment Path segments as a single string.
     * @param queryParams Optional query parameters.
     * @param headers Optional headers.
     */
    suspend inline fun <reified Response> delete(
        pathSegment: String,
        queryParams: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
    ): ApiResult<Response> =
        safeCall {
            client.delete {
                contentType(ContentType.Application.Json)
                headers.forEach { (key, value) -> header(key, value) }

                url {
                    appendPathSegments(pathSegment.toCleanPathSegments())
                    queryParams.forEach { (key, value) -> parameters.append(key, value) }
                }
            }.body()
        }

    /**
     * Safely executes the provided suspending [call] and returns the result wrapped in [ApiResult].
     */

    suspend inline fun <reified T> safeCall(noinline call: suspend () -> HttpResponse): ApiResult<T> {
        return backgroundScope {
            try {
                // Check internet connectivity before making the network call
                if (!internetManager.isConnected()) {
                    return@backgroundScope ApiResult.NoInternet
                }
                val response = call()
                when (response.status) {
                    in HttpStatusCode.OK..HttpStatusCode.MultiStatus -> {
                        val responseBody: T = response.body()
                        ApiResult.Success(responseBody)
                    }

                    else -> {
                        val responseStatus = allStatusCodes.find { it == response.status } ?: response.status
                        ApiResult.Error(message = responseStatus.description, code = responseStatus.value)
                    }
                }
            } catch (_: SocketTimeoutException) {
                ApiResult.Timeout
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                ApiResult.Unknown(message = e.message)
            }
        }
    }

    /**
     * Splits a URL-like path string into individual non-empty path segments.
     *
     * Example: "users/123" → ["users", "123"]
     */
    fun String.toCleanPathSegments(): List<String> {
        return takeIf { it.isNotBlank() }
            ?.split("/")
            ?.filter { it.isNotEmpty() }
            ?: emptyList()
    }
}
