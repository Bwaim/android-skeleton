package com.bwaim.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.bwaim.examplefeature.ExampleFeature
import com.bwaim.navigation.ExampleDirections
import com.bwaim.navigation.NavigationManager

@Composable
public fun NavigationGraph(navigationManager: NavigationManager) {
    val navController = rememberNavController()
    navigationManager.commands.collectAsState().value.also { command ->
        if (command.destination.isNotEmpty()) {
            navController.navigate(command.destination)
        }
    }
    NavHost(
        navController,
        startDestination = ExampleDirections.root.destination
    ) {
        navigation(
            startDestination = ExampleDirections.example.destination,
            route = ExampleDirections.root.destination
        ) {
            composable(ExampleDirections.example.destination) {
                val entry = remember {
                    navController.getBackStackEntry(
                        route = ExampleDirections.example.destination
                    )
                }
                ExampleFeature(hiltViewModel(entry))
            }
        }
    }
}
