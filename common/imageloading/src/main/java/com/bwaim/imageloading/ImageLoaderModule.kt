package com.bwaim.imageloading

import android.content.Context
import coil.Coil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ImageLoaderModule {
    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context
    ) = Coil.imageLoader(context)
}
