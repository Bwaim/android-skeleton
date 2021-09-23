package com.bwaim.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

@Composable
public fun SkeletonTheme(
    useDarkColors: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (useDarkColors) SkeletonDarkColors else SkeletonLightColors,
        typography = debugTypography(),
        shapes = Shapes,
        content = content
    )
}

public object SkeletonTheme {
    public val typography: SkeletonTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalSkeletonTypography.current
}
