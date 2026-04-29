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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.beleningalina.jetpackcomposepoc.ui.components.InfoCard
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing

/**
 * This counter works correctly because it uses Compose State properly.
 *
 * - mutableStateOf: observable state (triggers recomposition and updates the UI automatically)
 * - remember: keeps value across recompositions. Without it, the value would be recreated and reset on every recomposition.
 *
 * Limitation: state is lost on configuration changes (e.g., screen rotation, process recreation).
 * Recommended: rememberSaveable to persist state across configuration changes or ViewModel for proper lifecycle-aware state management.
 */
@Composable
fun CounterRememberScreen() {
    val counter: MutableState<Int> = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = AppSpacing.medium),
            text = AppScreen.CounterRememberScreen.title,
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
                title = "How state works in this counter?",
                message = "The counter works because it uses Compose state and remember correctly.",
                points = listOf(
                    "mutableStateOf triggers recomposition",
                    "remember keeps value across recomposition",
                    "State is not saved on configuration changes (e.g: screen rotation)"
                ),
                highlight = "Use rememberSaveable or ViewModel for persistence"
            )
        }
    }
}