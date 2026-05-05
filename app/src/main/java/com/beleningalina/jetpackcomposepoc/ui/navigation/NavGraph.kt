package com.beleningalina.jetpackcomposepoc.ui.navigation

import com.beleningalina.jetpackcomposepoc.ui.screens.product.ProductInfoScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beleningalina.jetpackcomposepoc.ui.screens.DisposableEffectScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.browser.UserBrowserScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterRememberCoroutineScopeScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterRememberSaveableScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterRememberScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.counter.CounterVariableScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.MainScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.profile.UserProfileScreen
import com.beleningalina.jetpackcomposepoc.ui.screens.voiceRecorder.VoiceRecorderScreen

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

    composable(route = AppScreen.CounterRememberCoroutineScopeScreen.route) {
        CounterRememberCoroutineScopeScreen()
    }

    composable(route = AppScreen.VoiceRecorderScreen.route) {
        VoiceRecorderScreen()
    }

    composable(route = AppScreen.ProductInfoScreen.route) {
        ProductInfoScreen()
    }

    composable(route = AppScreen.UserBrowserScreen.route) {
        UserBrowserScreen()
    }

    composable(route = AppScreen.DisposableEffectScreen.route) {
        DisposableEffectScreen()
    }
}