package com.beleningalina.jetpackcomposepoc.ui.navigation

import ProductInfoScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beleningalina.jetpackcomposepoc.ui.product.MockAnalytics
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterRememberCoroutineScopeScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterRememberSaveableScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterRememberScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterVariableScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterViewModel2Screen
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterViewModelScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.MainScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.profile.UserProfileScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.voiceRecorder.VoiceRecorderScreen
import com.beleningalina.jetpackcomposepoc.viewmodel.CounterViewModel2

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

    composable(route = AppScreen.CounterViewModelScreen.route) {
        CounterViewModelScreen()
    }

    composable(route = AppScreen.CounterViewModel2Screen.route) {  backStackEntry ->
        val viewModel: CounterViewModel2 = viewModel(backStackEntry)
        CounterViewModel2Screen(viewModel = viewModel)
    }

    composable(route = AppScreen.CounterRememberCoroutineScopeScreen.route) {
        CounterRememberCoroutineScopeScreen()
    }

    composable(route = AppScreen.VoiceRecorderScreen.route) {
        VoiceRecorderScreen()
    }

    composable(route = AppScreen.ProductInfoScreen.route) {
        ProductInfoScreen()
    }
}