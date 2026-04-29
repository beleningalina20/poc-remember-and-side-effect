package com.beleningalina.jetpackcomposepoc.ui.screens.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.beleningalina.jetpackcomposepoc.ui.components.InfoCard
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing

/**
 * This counter works correctly because it uses rememberSaveable to persist state across recompositions and configuration changes.
 *
 * - rememberSaveable: stores and restores state automatically using an internal Android SavedStateRegistry backed by a Bundle (you do not manage it directly)
 * - mutableStateOf: observable state (triggers recomposition and updates the UI automatically)
 * - remember: keeps value across recompositions (used internally by rememberSaveable)
 *
 * Limitation: only supports types that can be saved in a Bundle by default (e.g., Int, String, Boolean).
 * For complex objects, a custom Saver is required to define how to save and restore the state.
 *
 * Recommended: use ViewModel with SavedStateHandle for more complex or business-related state and better lifecycle-aware state management.
 */
@Composable
fun CounterRememberSaveableScreen() {
    val counter: MutableState<Int> = rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = AppSpacing.medium),
            text = AppScreen.CounterRememberSaveableScreen.title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        )

        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("${counter.value}")
                Button(
                    onClick = { counter.value++ }
                ) {
                    Text("+")
                }
            }

            Spacer(
                modifier = Modifier.height(AppSpacing.medium)
                    .weight(1f)
            )

            InfoCard(
                modifier = Modifier.padding(AppSpacing.small),
                title = "How does this counter survive rotation?",
                message = "This counter works because it uses rememberSaveable, which persists state across configuration changes.",
                points = listOf(
                    "State is saved automatically in an internal Bundle",
                    "Works out of the box for primitive types (Int, String, Boolean)",
                    "For complex objects, a custom Saver is required"
                ),
                highlight = "For scalable apps, prefer ViewModel + SavedStateHandle"
            )
        }

    }
}