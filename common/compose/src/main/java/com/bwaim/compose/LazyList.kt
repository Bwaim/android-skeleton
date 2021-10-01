package com.bwaim.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bwaim.compose.ListDefaults.VerticalPadding
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
public fun LazyListState.elevation(): Dp {
    return remember(this) {
        derivedStateOf {
            if (firstVisibleItemIndex == 0) {
                minOf(firstVisibleItemScrollOffset.toFloat().dp, AppBarDefaults.TopAppBarElevation)
            } else {
                AppBarDefaults.TopAppBarElevation
            }
        }
    }.value
}

@Composable
public fun LazyListState.isScrolled(): Boolean {
    return remember(this) {
        derivedStateOf { firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0 }
    }.value
}

public object ListDefaults {
    public val VerticalPadding: Dp = 8.dp
}

@Composable
public fun rememberListContentPaddingValues(
    additionalVertical: Dp = VerticalPadding
): PaddingValues = rememberInsetsPaddingValues(
    insets = LocalWindowInsets.current.navigationBars,
    applyStart = false,
    applyTop = false,
    applyEnd = false,
    additionalTop = additionalVertical,
    additionalBottom = additionalVertical
)
