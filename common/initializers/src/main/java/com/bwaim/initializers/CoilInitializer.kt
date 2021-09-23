package com.bwaim.initializers

import android.app.ActivityManager
import android.content.Context
import androidx.core.content.getSystemService
import androidx.startup.Initializer
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import com.bwaim.coroutines.IODispatcher
import com.bwaim.initializers.di.InitializerEntryPoint
import dagger.Lazy
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import javax.inject.Inject

internal class CoilInitializer : Initializer<ImageLoader> {
    @Inject
    lateinit var okHttpClientLazy: Lazy<OkHttpClient>

    @Inject
    @IODispatcher
    lateinit var ioDispatcher: CoroutineDispatcher

    override fun create(context: Context): ImageLoader {
        InitializerEntryPoint.resolve(context).inject(this)

        Coil.setImageLoader {
            ImageLoader.Builder(context)
                .apply {
                    val activityManager = context.getSystemService<ActivityManager>()!!
                    allowRgb565(activityManager.isLowRamDevice)

                    dispatcher(ioDispatcher)

                    val coilOkHttpClient by lazy {
                        okHttpClientLazy.get().newBuilder()
                            .cache(CoilUtils.createDefaultCache(context))
                            .build()
                    }
                    callFactory { request -> coilOkHttpClient.newCall(request) }
                }
                .build()
        }

        return Coil.imageLoader(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
