package com.bwaim.http.utils

import io.mockk.mockk
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.net.HttpURLConnection

internal object HttpTestUtils {
    fun createFakeResponse(
        code: Int = HttpURLConnection.HTTP_OK,
        request: Request = mockk(relaxed = true),
        body: ResponseBody? = null
    ) = Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_2)
        .message("")
        .code(code)
        .body(body)
        .build()
}
