package com.bwaim.theme

import kotlinx.coroutines.flow.Flow

public interface ThemeRepository {
    public fun observeTheme(): Flow<Theme>

    public suspend fun setTheme(theme: Theme)
}
