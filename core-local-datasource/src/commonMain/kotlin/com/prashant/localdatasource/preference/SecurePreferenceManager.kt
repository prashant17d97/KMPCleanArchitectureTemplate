package com.prashant.localdatasource.preference

import com.liftric.kvault.KVault
import com.prashant.core.domain.repository.local.PreferenceStore

class SecurePreferenceManager(
    kVault: KVault,
) : PreferenceStore {
    private var editor = kVault

    override fun putString(
        key: String,
        value: String,
    ) {
        editor.set(key, value)
    }

    override fun putInt(
        key: String,
        value: Int,
    ) {
        editor.set(key, value)
    }

    override fun putLong(
        key: String,
        value: Long,
    ) {
        editor.set(key, value)
    }

    override fun putFloat(
        key: String,
        value: Float,
    ) {
        editor.set(key, value)
    }

    override fun putBoolean(
        key: String,
        value: Boolean,
    ) {
        editor.set(key, value)
    }

    override fun getString(
        key: String,
        defaultValue: String?,
    ): String? = editor.string(key) ?: defaultValue

    override fun getInt(
        key: String,
        defaultValue: Int,
    ): Int = editor.int(key) ?: defaultValue

    override fun getLong(
        key: String,
        defaultValue: Long,
    ): Long = editor.long(key) ?: defaultValue

    override fun getFloat(
        key: String,
        defaultValue: Float,
    ): Float = editor.float(key) ?: defaultValue

    override fun getBoolean(
        key: String,
        defaultValue: Boolean,
    ): Boolean = editor.bool(key) ?: defaultValue

    override fun remove(key: String) {
        editor.deleteObject(key)
    }

    override fun clear() {
        editor.clear()
    }
}
