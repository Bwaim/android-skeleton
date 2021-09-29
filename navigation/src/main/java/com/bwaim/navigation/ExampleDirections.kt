package com.bwaim.navigation

import androidx.navigation.NamedNavArgument
import com.bwaim.common.navigation.NavigationCommand

public object ExampleDirections {
    public val Default: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = ""
    }

    public val root: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "example_graph"
    }

    public val example: NavigationCommand = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "example"
    }
}
