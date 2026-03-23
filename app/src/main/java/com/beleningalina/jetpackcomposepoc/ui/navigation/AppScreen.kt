package com.beleningalina.jetpackcomposepoc.ui.navigation

import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_VARIABLE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.MAIN_SCREEN_ROUTE

object AppDestination {
    const val MAIN_SCREEN_ROUTE = "main"
    const val COUNTER_VARIABLE = "counter_variable"
}

sealed class AppScreen(val title: String, val route: String) {
    object Main: AppScreen("Remembers and Side Effects", MAIN_SCREEN_ROUTE)
    object CounterVariable: AppScreen("Counter using a common variable", COUNTER_VARIABLE)

    companion object {
        val screens = listOf(CounterVariable)
    }
}