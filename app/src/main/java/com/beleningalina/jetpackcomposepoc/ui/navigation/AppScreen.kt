package com.beleningalina.jetpackcomposepoc.ui.navigation

import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_REMEMBER_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_REMEMBER_SAVEABLE_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_VARIABLE_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_VIEW_MODEL_ROUTE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.MAIN_SCREEN_ROUTE

object AppDestination {
    const val MAIN_SCREEN_ROUTE = "main"
    const val COUNTER_VARIABLE_ROUTE = "counter_variable"
    const val COUNTER_REMEMBER_ROUTE = "counter_remember"
    const val COUNTER_REMEMBER_SAVEABLE_ROUTE = "counter_remember_saveable"
    const val COUNTER_VIEW_MODEL_ROUTE = "counter_view_model"
}

sealed class AppScreen(val title: String, val route: String) {
    object Main: AppScreen("Remembers and Side Effects", MAIN_SCREEN_ROUTE)
    object CounterVariable: AppScreen("Counter using a common variable", COUNTER_VARIABLE_ROUTE)
    object CounterRemember: AppScreen("Counter using a mutableState and remember", COUNTER_REMEMBER_ROUTE)
    object CounterRememberSaveable: AppScreen("Counter using rememberSaveable", COUNTER_REMEMBER_SAVEABLE_ROUTE)
    object CounterViewModel: AppScreen("Counter using ViewModel", COUNTER_VIEW_MODEL_ROUTE)

    companion object {
        val screens = listOf(CounterVariable, CounterRemember, CounterRememberSaveable, CounterViewModel)
    }
}