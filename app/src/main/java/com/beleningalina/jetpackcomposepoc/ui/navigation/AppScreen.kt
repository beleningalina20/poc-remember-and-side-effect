package com.beleningalina.jetpackcomposepoc.ui.navigation

import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_REMEMBER_COROUTINE_SCOPE_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_REMEMBER_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_REMEMBER_SAVEABLE_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_VARIABLE_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_VIEW_MODEL_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_VIEW_MODEL_SAVED_STATE_HANDLE_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.MAIN_SCREEN_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.PRODUCT_INFO_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.USER_BROWSER_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.USER_PROFILE_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.VOICE_RECORDER_ROUTE

object AppDestination {
    const val MAIN_SCREEN_ROUTE = "main"
    const val COUNTER_VARIABLE_ROUTE = "counter_variable"
    const val COUNTER_REMEMBER_ROUTE = "counter_remember"
    const val COUNTER_REMEMBER_SAVEABLE_ROUTE = "counter_remember_saveable"
    const val USER_PROFILE_ROUTE = "user_profile"
    const val COUNTER_VIEW_MODEL_ROUTE = "counter_view_model"
    const val COUNTER_VIEW_MODEL_SAVED_STATE_HANDLE_ROUTE = "counter_view_model_saved_state_handle"
    const val COUNTER_REMEMBER_COROUTINE_SCOPE_ROUTE = "counter_remember_coroutine_scope"
    const val VOICE_RECORDER_ROUTE = "voice_recorder_screen"
    const val PRODUCT_INFO_ROUTE = "product_info_route"
    const val USER_BROWSER_ROUTE = "user_browser_route"
}

sealed class AppScreen(val title: String, val route: String) {
    object Main: AppScreen("Remembers and Side Effects", MAIN_SCREEN_ROUTE)
    object CounterVariableScreen: AppScreen("Counter: common variable", COUNTER_VARIABLE_ROUTE)
    object CounterRememberScreen: AppScreen("Counter: mutableState + remember", COUNTER_REMEMBER_ROUTE)
    object CounterRememberSaveableScreen: AppScreen("Counter: rememberSaveable", COUNTER_REMEMBER_SAVEABLE_ROUTE)
    object UserProfileScreen: AppScreen("User Profile: rememberSaveable + saver", USER_PROFILE_ROUTE)
    object CounterViewModelScreen: AppScreen("Counter using ViewModel", COUNTER_VIEW_MODEL_ROUTE)
    object CounterViewModel2Screen: AppScreen("Counter using ViewModel + savedStateHandle", COUNTER_VIEW_MODEL_SAVED_STATE_HANDLE_ROUTE)
    object CounterRememberCoroutineScopeScreen: AppScreen("Counter: rememberCoroutineScope", COUNTER_REMEMBER_COROUTINE_SCOPE_ROUTE)
    object VoiceRecorderScreen: AppScreen("Voice Recorder: rememberUpdatedState", VOICE_RECORDER_ROUTE)
    object ProductInfoScreen: AppScreen("Product info: SideEffect", PRODUCT_INFO_ROUTE)
    object UserBrowserScreen: AppScreen("User Browser: LaunchedEffect", USER_BROWSER_ROUTE)

    companion object {
        val screens = listOf(
            CounterVariableScreen,
            CounterRememberScreen,
            CounterRememberSaveableScreen,
            UserProfileScreen,
            CounterViewModelScreen,
            CounterViewModel2Screen,
            CounterRememberCoroutineScopeScreen,
            VoiceRecorderScreen,
            ProductInfoScreen,
            UserBrowserScreen
        )
    }
}