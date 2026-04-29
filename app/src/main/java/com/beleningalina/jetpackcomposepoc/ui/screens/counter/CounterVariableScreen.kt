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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.beleningalina.jetpackcomposepoc.ui.components.InfoCard
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing

/**
 * This counter does NOT work as expected.
 *
 * - `counter` is a regular Int variable, not a State
 * - Updating a normal variable does not trigger recomposition
 * - Jetpack Compose only observes State changes
 * - As a result, the UI never updates
 *
 * Not every variable is State.
 * Use State (read-only) or MutableState (mutable) to update the UI.
 */
@Composable
fun CounterVariableScreen() {
    var counter: Int = 0

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = AppSpacing.medium),
            text = AppScreen.CounterVariableScreen.title,
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
                Text("$counter")
                Button(
                    onClick = { counter++ }
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
                title = "Why isn't it updating?",
                message = "This counter doesn’t update because it's not using State.",
                points = listOf(
                    "Regular variables don’t trigger recomposition",
                    "Compose only reacts to State changes"
                ),
                highlight = "Use remember { mutableStateOf(0) }"
            )
        }
    }
}