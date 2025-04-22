package com.prashant.core.utils

import com.plusmobileapps.konnectivity.Konnectivity
import com.plusmobileapps.konnectivity.NetworkConnection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class InternetConnectivityManager {
    actual fun isConnected(): Boolean {
        return Konnectivity().isConnected
    }

    actual fun observeSmartConnectivity(): Flow<Boolean> {
        return callbackFlow {
            when (Konnectivity().currentNetworkConnectionState.value) {
                NetworkConnection.NONE -> trySend(false).isSuccess
                NetworkConnection.WIFI -> trySend(true).isSuccess
                NetworkConnection.CELLULAR -> trySend(true).isSuccess
            }
        }
    }
}
