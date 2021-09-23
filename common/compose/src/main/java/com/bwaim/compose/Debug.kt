@file:Suppress("NOTHING_TO_INLINE")

package com.bwaim.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import timber.log.Timber

@PublishedApi()
internal class Ref(var value: Int)

/**
 * An effect which logs the number compositions at the invoked point of the slot table.
 *
 * This is an inline function to act as like a C-style #include to the host composable function.
 * That way we track it's compositions, not this function's compositions.
 *
 * @param tag Log tag used for [Logger.d].
 */
@Composable
public inline fun LogCompositions(tag: String) {
    if (BuildConfig.DEBUG) {
        val ref = remember { Ref(0) }
        SideEffect { ref.value++ }
        Timber.d(tag, "Compositions: ${ref.value}")
    }
}
