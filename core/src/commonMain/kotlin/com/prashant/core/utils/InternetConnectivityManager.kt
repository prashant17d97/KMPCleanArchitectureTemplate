package com.prashant.core.utils

import kotlinx.coroutines.flow.Flow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class InternetConnectivityManager() {
    fun isConnected(): Boolean

    fun observeSmartConnectivity(): Flow<Boolean>
}
