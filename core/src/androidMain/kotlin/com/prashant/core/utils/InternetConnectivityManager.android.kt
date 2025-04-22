package com.prashant.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class InternetConnectivityManager : KoinComponent {
    private val context: Context by inject()
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var initialStateHandled = false

    /**
     * Checks current internet availability.
     */
    actual fun isConnected(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    /**
     * Emits connectivity status (true = connected, false = disconnected) with smart filtering.
     */
    actual fun observeSmartConnectivity(): Flow<Boolean> =
        callbackFlow {
            val networkCallback =
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        if (initialStateHandled) {
                            trySend(true).isSuccess
                        }
                    }

                    override fun onLost(network: Network) {
                        trySend(false).isSuccess

                        // Handle the initial state only once
                        initialStateHandled = true
                    }
                }

            val request =
                NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()

            connectivityManager.registerNetworkCallback(request, networkCallback)

            val isConnected = isConnected()

            // Show no internet if disconnected on launch
            if (!isConnected) {
                trySend(false).isSuccess
            }

            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }
}
