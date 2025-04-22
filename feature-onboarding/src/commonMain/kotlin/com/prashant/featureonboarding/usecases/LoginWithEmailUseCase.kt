package com.prashant.featureonboarding.usecases

import com.prashant.core.domain.models.network.ApiResult
import com.prashant.core.domain.models.network.LoginRequestBody
import com.prashant.core.domain.repository.remote.AuthRepository

class LoginWithEmailUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(loginRequestBody: LoginRequestBody): ApiResult<Unit> = authRepository.loginWithEmail(loginRequestBody)
}
