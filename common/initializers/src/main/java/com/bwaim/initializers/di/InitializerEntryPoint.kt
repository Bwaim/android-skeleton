package com.bwaim.initializers.di

import android.content.Context
import com.bwaim.initializers.WorkManagerInitializer
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface InitializerEntryPoint {
    fun inject(initializer: WorkManagerInitializer)

    companion object {
        fun resolve(context: Context): InitializerEntryPoint {
            return EntryPointAccessors.fromApplication(
                requireNotNull(context.applicationContext),
                InitializerEntryPoint::class.java
            )
        }
    }
}
