package com.prashant.core.domain.repository.remote

import com.prashant.core.domain.models.network.ApiResult
import com.prashant.core.domain.models.network.LoginRequestBody

interface AuthRepository {
    suspend fun loginWithEmail(loginRequestBody: LoginRequestBody): ApiResult<Unit>
}
