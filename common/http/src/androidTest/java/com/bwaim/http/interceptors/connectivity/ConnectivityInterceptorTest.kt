package com.bwaim.http.interceptors.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.core.content.getSystemService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import org.junit.Before
import org.junit.Test

internal class ConnectivityInterceptorTest {
    @RelaxedMockK
    private lateinit var mockConnectivityManager: ConnectivityManager

    @RelaxedMockK
    private lateinit var mockContext: Context

    private lateinit var connectivityInterceptor: ConnectivityInterceptor

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { mockContext.getSystemService<ConnectivityManager>() } returns mockConnectivityManager

        connectivityInterceptor = ConnectivityInterceptor(mockContext)
    }

    @Test(expected = NoConnectivityException::class)
    fun noConnectivityExceptionThrown_whenDeviceNotConnectedToInternet() {
        mockConnectivity(false)

        connectivityInterceptor.intercept(mockk(relaxed = true))
    }

    @Test
    fun requestProceeded_whenDeviceConnectedToInternet() {
        mockConnectivity(true)
        val fakeRequest = createFakeRequest()
        val chain = mockk<Interceptor.Chain>(relaxed = true).apply {
            every { request() } returns fakeRequest
        }

        connectivityInterceptor.intercept(chain)

        verify {
            chain.proceed(fakeRequest)
        }
    }

    private fun mockConnectivity(connected: Boolean) {
        if (Build.VERSION.SDK_INT < 24) {
            every { mockConnectivityManager.activeNetworkInfo } returns mockk<NetworkInfo>(relaxed = true).apply {
                every { isConnectedOrConnecting } returns connected
            }
        } else {
            val mockNetworkCapabilities = mockk<NetworkCapabilities>()
            every { mockNetworkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns connected
            connectivityInterceptor.onCapabilitiesChanged(mockk<Network>(), mockNetworkCapabilities)
        }
    }

    private fun createFakeRequest(
        url: String = "http://test.fr",
        method: String = "GET",
        body: RequestBody? = null
    ) = Request.Builder()
        .url(url)
        .method(method, body)
        .build()
}
