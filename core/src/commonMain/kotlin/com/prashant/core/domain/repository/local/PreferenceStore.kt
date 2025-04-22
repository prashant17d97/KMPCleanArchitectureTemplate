package com.prashant.core.domain.repository.local

/**
 * Interface for storing and retrieving data from a preference store.
 * This is an abstraction over a concrete preference store implementation, like SharedPreferences.
 */
interface PreferenceStore {
    /**
     * Puts a string value into the preference store.
     *
     * @param key The key under which the value should be stored.
     * @param value The string value to store. Can be null.
     */
    fun putString(
        key: String,
        value: String,
    )

    /**
     * Retrieves a string value from the preference store.
     *
     * @param key The key of the value to retrieve.
     * @param defaultValue The default value to return if the key does not exist.
     * @return The string value associated with the key, or the default value if the key does not exist.
     */
    fun getString(
        key: String,
        defaultValue: String?,
    ): String?

    /**
     * Puts an integer value into the preference store.
     *
     * @param key The key under which the value should be stored.
     * @param value The integer value to store.
     */
    fun putInt(
        key: String,
        value: Int,
    )

    /**
     * Retrieves an integer value from the preference store.
     *
     * @param key The key of the value to retrieve.
     * @param defaultValue The default value to return if the key does not exist.
     * @return The integer value associated with the key, or the default value if the key does not exist.
     */
    fun getInt(
        key: String,
        defaultValue: Int,
    ): Int

    /**
     * Puts a boolean value into the preference store.
     *
     * @param key The key under which the value should be stored.
     * @param value The boolean value to store.
     */
    fun putBoolean(
        key: String,
        value: Boolean,
    )

    /**
     * Retrieves a boolean value from the preference store.
     *
     * @param key The key of the value to retrieve.
     * @param defaultValue The default value to return if the key does not exist.
     * @return The boolean value associated with the key, or the default value if the key does not exist.
     */
    fun getBoolean(
        key: String,
        defaultValue: Boolean,
    ): Boolean

    /**
     * Puts a long value into the preference store.
     *
     * @param key The key under which the value should be stored.
     * @param value The long value to store.
     */
    fun putLong(
        key: String,
        value: Long,
    )

    /**
     * Retrieves a long value from the preference store.
     *
     * @param key The key of the value to retrieve.
     * @param defaultValue The default value to return if the key does not exist.
     * @return The long value associated with the key, or the default value if the key does not exist.
     */
    fun getLong(
        key: String,
        defaultValue: Long,
    ): Long

    fun putFloat(
        key: String,
        value: Float,
    )

    fun getFloat(
        key: String,
        defaultValue: Float,
    ): Float

    /**
     * Removes a value associated with a key from the preference store.
     *
     * @param key The key of the value to remove.
     */
    fun remove(key: String)

    /**
     * Clears all data from the preference store.
     *
     * This will remove all key-value pairs.
     */
    fun clear()
}
