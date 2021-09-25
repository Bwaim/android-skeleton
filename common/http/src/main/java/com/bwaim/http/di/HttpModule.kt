package com.bwaim.http.di

import android.content.Context
import com.bwaim.http.BuildConfig
import com.bwaim.http.interceptors.connectivity.ConnectivityInterceptor
import com.bwaim.http.interceptors.emptybody.EmptyBodyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT = 30_000L // in ms

@Module
@InstallIn(SingletonComponent::class)
internal object HttpModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        connectivityInterceptor: ConnectivityInterceptor,
        emptyBodyInterceptor: EmptyBodyInterceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .addNetworkInterceptor(connectivityInterceptor)
        .addInterceptor(emptyBodyInterceptor)
        .apply {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(loggingInterceptor)
            }
        }
        .cache(Cache(File(context.cacheDir, "http_cache"), 50L * 1024L * 1024L))
        .build()
}
