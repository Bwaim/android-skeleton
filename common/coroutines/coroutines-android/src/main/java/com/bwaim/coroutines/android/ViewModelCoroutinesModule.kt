package com.bwaim.coroutines.android

import com.bwaim.coroutines.MainDispatcher
import com.bwaim.coroutines.ViewModelScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelCoroutinesModule {
    @Provides
    @ViewModelScoped
    @ViewModelScope
    fun provideViewModelScope(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ) = CoroutineScope(SupervisorJob() + mainDispatcher)
}
