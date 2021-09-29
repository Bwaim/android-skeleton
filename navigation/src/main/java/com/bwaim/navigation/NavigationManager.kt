package com.bwaim.navigation

import com.bwaim.common.navigation.NavigationCommand
import com.bwaim.navigation.ExampleDirections.Default
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class NavigationManager @Inject constructor() {
    public var commands: MutableStateFlow<NavigationCommand> = MutableStateFlow(Default)

    public fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}
