package com.beleningalina.jetpackcomposepoc.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beleningalina.jetpackcomposepoc.ui.screens.CounterRememberSaveableScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.CounterRememberScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.CounterVariableScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.MainScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.profile.UserProfileScreen

fun NavGraphBuilder.addFeedScreenGraph(navController: NavController) {
    composable(route = AppScreen.Main.route) {
        MainScreen(
            navigateTo = { route ->
                navController.navigate(route)
            }
        )
    }

    composable(route = AppScreen.CounterVariableScreen.route) {
        CounterVariableScreen()
    }

    composable(route = AppScreen.CounterRememberScreen.route) {
        CounterRememberScreen()
    }

    composable(route = AppScreen.CounterRememberSaveableScreen.route) {
        CounterRememberSaveableScreen()
    }

    composable(route = AppScreen.UserProfileScreen.route) {
        UserProfileScreen()
    }
}