package com.prashant.localdatasource.preference

import com.prashant.core.domain.repository.local.PreferenceStore
import com.prashant.core.domain.repository.local.TokenManager

class TokenManagerImpl(private val preferenceStore: PreferenceStore) : TokenManager {
    companion object {
        private const val BEARER_TOKEN = "bearer_token"
    }

    override fun saveToken(token: String) {
        val bearerToken =
            if (token.startsWith("Bearer")) {
                token
            } else {
                "Bearer $token"
            }
        preferenceStore.putString(BEARER_TOKEN, bearerToken)
    }

    override fun getToken(): String? {
        return preferenceStore.getString(BEARER_TOKEN, null)
    }

    override fun hasToken(): Boolean {
        return preferenceStore.getString(BEARER_TOKEN, null) != null
    }

    override fun clearToken() {
        preferenceStore.remove(BEARER_TOKEN)
    }
}
