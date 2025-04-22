package com.prashant.core.domain.models.network

import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResult<out T> {
    @Serializable
    data class Success<T>(val data: T) : ApiResult<T>()

    @Serializable
    data class Error(val message: String? = null, val code: Int? = null) : ApiResult<Nothing>()

    @Serializable
    data object Timeout : ApiResult<Nothing>()

    @Serializable
    data object NoInternet : ApiResult<Nothing>()

    @Serializable
    data class Unknown(val message: String? = null) : ApiResult<Nothing>()
}
