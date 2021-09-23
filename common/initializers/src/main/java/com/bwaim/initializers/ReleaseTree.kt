package com.bwaim.initializers

import android.util.Log
import com.bwaim.initializers.RemoteCrashKeys.KEY_MESSAGE
import com.bwaim.initializers.RemoteCrashKeys.KEY_PRIORITY
import com.bwaim.initializers.RemoteCrashKeys.KEY_TAG
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

internal class ReleaseTree : Timber.Tree() {
    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }

        crashlytics.run {
            setCustomKey(KEY_PRIORITY, priority)
            setCustomKey(KEY_TAG, tag ?: "")
            setCustomKey(KEY_MESSAGE, message)
        }

        val exception = t ?: Exception(message)
        crashlytics.recordException(exception)
    }
}
