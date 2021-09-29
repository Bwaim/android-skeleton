package com.bwaim.common.navigation

import androidx.navigation.NamedNavArgument

public interface NavigationCommand {
    public val arguments: List<NamedNavArgument>
    public val destination: String
}
