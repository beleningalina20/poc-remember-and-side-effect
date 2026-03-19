package com.beleningalina.jetpackcomposepoc.ui.navigation

import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_VARIABLE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_MUTABLE_STATE
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.COUNTER_REMEMBER
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppDestination.MAIN_SCREEN_ROUTE

object AppDestination {
    const val MAIN_SCREEN_ROUTE = "main"
    const val COUNTER_VARIABLE = "counter_variable"
    const val COUNTER_MUTABLE_STATE = "counter_mutable_state"
    const val COUNTER_REMEMBER = "counter_remember"
}

sealed class AppScreen(val title: String, val route: String) {
    object Main: AppScreen("Remember and Side Effects", MAIN_SCREEN_ROUTE)
    object CounterVariable: AppScreen("Variable", COUNTER_VARIABLE)
    object CounterMutableState: AppScreen("Mutable State", COUNTER_MUTABLE_STATE)
    object CounterRemember: AppScreen("Remember", COUNTER_REMEMBER)

    companion object {
        val screens = listOf(CounterVariable, CounterMutableState, CounterRemember)
    }
}