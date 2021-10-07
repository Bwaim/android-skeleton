package com.bwaim.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.bwaim.examplefeature.ExampleFeature
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

internal sealed class Screen(val route: String) {
    object MainMenu : Screen("mainMenu")
}

private sealed class LeafScreen(
    private val route: String,
) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object MainMenu : LeafScreen("mainMenu")
}

// TODO: remove the transition or change it when https://github.com/google/accompanist/issues/773
//  is solved
@ExperimentalAnimationApi
@Composable
internal fun AppNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController,
        startDestination = Screen.MainMenu.route,
        enterTransition = { _, _ -> fadeIn(animationSpec = tween(700)) },
        exitTransition = { _, _ -> fadeOut(animationSpec = tween(700)) }
    ) {
        addMainMenuTopLevel(navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addMainMenuTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.MainMenu.route,
        startDestination = LeafScreen.MainMenu.createRoute(Screen.MainMenu)
    ) {
        addMainMenu(navController, Screen.MainMenu)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addMainMenu(
    navController: NavController,
    root: Screen
) {
    composable(LeafScreen.MainMenu.createRoute(root)) {
        val entry = remember {
            navController.getBackStackEntry(
                route = LeafScreen.MainMenu.createRoute(root)
            )
        }
        ExampleFeature(hiltViewModel(entry))
    }
}
