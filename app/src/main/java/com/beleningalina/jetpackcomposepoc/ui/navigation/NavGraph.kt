package com.beleningalina.jetpackcomposepoc.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beleningalina.jetpackcomposepoc.ui.screens.CounterRememberSaveableScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.CounterRememberScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.CounterVariableScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.MainScreen

fun NavGraphBuilder.addFeedScreenGraph(navController: NavController) {
    composable(route = AppScreen.Main.route) {
        MainScreen(
            navigateTo = { route ->
                navController.navigate(route)
            }
        )
    }

    composable(route = AppScreen.CounterVariable.route) {
        CounterVariableScreen()
    }

    composable(route = AppScreen.CounterRemember.route) {
        CounterRememberScreen()
    }

    composable(route = AppScreen.CounterRememberSaveable.route) {
        CounterRememberSaveableScreen()
    }
}