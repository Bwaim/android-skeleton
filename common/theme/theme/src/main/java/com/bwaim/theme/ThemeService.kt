package com.bwaim.theme

import com.bwaim.coroutines.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

public class ThemeService @Inject internal constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val themeRepository: ThemeRepository
) {
    public fun observeTheme(): Flow<Theme> {
        return themeRepository
            .observeTheme()
            .flowOn(ioDispatcher)
    }

    public suspend fun setTheme(theme: Theme) {
        withContext(ioDispatcher) {
            themeRepository.setTheme(theme)
        }
    }
}
