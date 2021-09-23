package com.bwaim.initializers

import android.content.Context
import android.os.Build
import androidx.startup.Initializer
import timber.log.Timber
import java.util.regex.Pattern

internal class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val tree = if (BuildConfig.DEBUG) {
            SkeletonDebugTree()
        } else {
            ReleaseTree()
        }
        Timber.plant(tree)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    /**
     * Special version of [Timber.DebugTree] which is tailored for Timber being wrapped
     * within another class.
     */
    private class SkeletonDebugTree : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            super.log(priority, createClassTag(), message, t)
        }

        private fun createClassTag(): String {
            val stackTrace = Throwable().stackTrace
            check(stackTrace.size > CALL_STACK_INDEX) { "Synthetic stacktrace didn't have enough elements: are you using proguard?" }
            var tag = stackTrace[CALL_STACK_INDEX].className
            val m = ANONYMOUS_CLASS.matcher(tag)
            if (m.find()) {
                tag = m.replaceAll("")
            }
            tag = tag.substring(tag.lastIndexOf('.') + 1)
            // Tag length limit was removed in API 24.
            return if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= 24) {
                tag
            } else {
                tag.substring(
                    0,
                    MAX_TAG_LENGTH
                )
            }
        }

        private companion object {
            const val MAX_TAG_LENGTH = 23
            const val CALL_STACK_INDEX = 7
            val ANONYMOUS_CLASS: Pattern = Pattern.compile("(\\$\\d+)+$")
        }
    }
}
