package com.bwaim.http.interceptors.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class ConnectivityInterceptor @Inject constructor(
    @ApplicationContext context: Context
) : Interceptor, ConnectivityManager.NetworkCallback() {
    private val connectivityManager = context.getSystemService<ConnectivityManager>()!!
    private var connected = false

    init {
        if (Build.VERSION.SDK_INT >= 24) {
            connectivityManager.registerDefaultNetworkCallback(this)
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (Build.VERSION.SDK_INT < 24) {
            connected = connectivityManager.activeNetworkInfo?.isConnected ?: false
        }

        if (!connected) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }

    override fun onCapabilitiesChanged(
        network: Network,
        capabilities: NetworkCapabilities
    ) {
        connected = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
