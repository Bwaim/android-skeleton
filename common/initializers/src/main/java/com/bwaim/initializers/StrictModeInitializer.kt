package com.bwaim.initializers

import android.content.Context
import android.os.StrictMode
import androidx.startup.Initializer

internal class StrictModeInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
                .also { StrictMode.setThreadPolicy(it) }

            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
                .also { StrictMode.setVmPolicy(it) }
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
