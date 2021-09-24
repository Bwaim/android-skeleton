package com.bwaim.initializers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import com.bwaim.initializers.di.InitializerEntryPoint
import javax.inject.Inject

internal class WorkManagerInitializer : Initializer<WorkManager> {
    @Inject
    internal lateinit var workerFactory: HiltWorkerFactory

    override fun create(context: Context): WorkManager {
        InitializerEntryPoint.resolve(context).inject(this)

        val configuration = Configuration.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    setMinimumLoggingLevel(Log.DEBUG)
                }
                setWorkerFactory(workerFactory)
            }
            .build()
        WorkManager.initialize(context, configuration)

        return WorkManager.getInstance(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
