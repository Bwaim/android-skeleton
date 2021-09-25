package com.bwaim.http.interceptors.emptybody

import androidx.annotation.VisibleForTesting
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection.HTTP_NO_CONTENT
import java.net.HttpURLConnection.HTTP_RESET
import javax.inject.Inject

internal class EmptyBodyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful || (response.code != HTTP_NO_CONTENT && response.code != HTTP_RESET)) {
            return response
        }

        return response
            .newBuilder()
            .code(200)
            .apply {
                val body = response.body
                if (body == null || body.contentLength() == 0L) {
                    body(EMPTY_BODY)
                }
            }
            .build()
    }

    companion object {
        @VisibleForTesting
        val EMPTY_BODY = "".toResponseBody("text/plain".toMediaType())
    }
}
