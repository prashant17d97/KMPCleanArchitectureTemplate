package com.prashant.coredata.repository

import com.prashant.core.domain.models.network.ApiResult
import com.prashant.core.domain.models.network.LoginRequestBody
import com.prashant.core.domain.repository.remote.AuthRepository
import com.prashant.network.BaseApiService

class AuthRepositoryImpl(private val baseApiService: BaseApiService) : AuthRepository {
    override suspend fun loginWithEmail(loginRequestBody: LoginRequestBody): ApiResult<Unit> {
        return baseApiService.post<Unit, LoginRequestBody>(
            pathSegment = "api/login",
            queryParams = mapOf(),
            headers = mapOf(),
            body = loginRequestBody,
        )
    }
}
