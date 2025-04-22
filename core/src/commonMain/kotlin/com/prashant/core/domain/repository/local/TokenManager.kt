package com.prashant.core.domain.repository.local

/**
 * TokenManager is responsible for managing the authentication token.
 *
 * It provides methods to save, retrieve, check existence, and clear the token.
 */
interface TokenManager {
    /**
     * Saves the authentication token.
     *
     * @param token The token to be saved.
     */
    fun saveToken(token: String)

    /**
     * Retrieves the authentication token.
     *
     * @return The saved token, or null if not present.
     */
    fun getToken(): String?

    /**
     * Checks if the authentication token exists.
     *
     * @return True if the token exists, false otherwise.
     */
    fun hasToken(): Boolean

    /**
     * Clears the authentication token.
     *
     * This method removes the saved token from storage.
     */
    fun clearToken()
}
