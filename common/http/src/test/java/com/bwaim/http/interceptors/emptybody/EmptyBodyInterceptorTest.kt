package com.bwaim.http.interceptors.emptybody

import com.bwaim.http.interceptors.emptybody.EmptyBodyInterceptor.Companion.EMPTY_BODY
import com.bwaim.http.utils.HttpTestUtils.createFakeResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import okhttp3.Interceptor
import okhttp3.Response
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

internal class EmptyBodyInterceptorTest {
    @RelaxedMockK
    private lateinit var mockChain: Interceptor.Chain

    private lateinit var emptyBodyInterceptor: Interceptor

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        emptyBodyInterceptor = EmptyBodyInterceptor()
    }

    @Test
    fun responseContainsEmptyBody_whenInitialResponseBodyIsNull() {
        mockChainProceed(createFakeResponse(code = HttpURLConnection.HTTP_NO_CONTENT))

        val body = emptyBodyInterceptor.intercept(mockChain).body

        assertThat(body).isEqualTo(EMPTY_BODY)
    }

    @Test
    fun responseContainsEmptyBody_whenInitialResponseBodyIsEmpty() {
        mockChainProceed(
            createFakeResponse(
                code = HttpURLConnection.HTTP_NO_CONTENT,
                body = mockk(relaxed = true)
            )
        )

        val body = emptyBodyInterceptor.intercept(mockChain).body

        assertThat(body).isEqualTo(EMPTY_BODY)
    }

    @Test
    fun responseCodeIsOk_whenInitialResponseCodeIsNoContent() {
        mockChainProceed(createFakeResponse(code = HttpURLConnection.HTTP_NO_CONTENT))

        val code = emptyBodyInterceptor.intercept(mockChain).code

        assertThat(code).isEqualTo(HttpURLConnection.HTTP_OK)
    }

    @Test
    fun responseCodeIsOk_whenInitialResponseCodeIsReset() {
        mockChainProceed(createFakeResponse(code = HttpURLConnection.HTTP_RESET))

        val code = emptyBodyInterceptor.intercept(mockChain).code

        assertThat(code).isEqualTo(HttpURLConnection.HTTP_OK)
    }

    @Test
    fun responseUnchanged_whenInitialResponseIsNotSuccessful() {
        val fakeResponse = createFakeResponse(code = HttpURLConnection.HTTP_NOT_FOUND)
        mockChainProceed(fakeResponse)

        val response = emptyBodyInterceptor.intercept(mockChain)

        assertThat(response).isEqualTo(fakeResponse)
    }

    @Test
    fun responseUnchanged_whenInitialResponseSuccessful() {
        val fakeResponse = createFakeResponse(code = HttpURLConnection.HTTP_OK)
        mockChainProceed(fakeResponse)

        val response = emptyBodyInterceptor.intercept(mockChain)

        assertThat(response).isEqualTo(fakeResponse)
    }

    private fun mockChainProceed(response: Response) {
        every { mockChain.proceed(any()) } returns response
    }
}
