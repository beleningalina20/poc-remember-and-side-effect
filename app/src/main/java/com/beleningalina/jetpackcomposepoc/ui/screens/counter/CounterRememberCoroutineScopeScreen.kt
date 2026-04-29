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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.beleningalina.jetpackcomposepoc.ui.components.InfoCard
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * This example uses rememberCoroutineScope to manage asynchronous tasks (like a 3-second delay)
 * directly tied to this Composable's lifecycle.
 *
 * - rememberCoroutineScope: provides a scope that is automatically cancelled when the Composable leaves the UI hierarchy.
 * - scope.launch: triggers the coroutine in response to a user event (the button click) without blocking the UI.
 *
 * Advantage: Automatic cancellation prevents memory leaks if the user navigates away.
 * Disadvantage: The running coroutine is lost on configuration changes (like rotation) because the scope is tied to the Composable's lifecycle.
 */
@Composable
fun CounterRememberCoroutineScopeScreen() {
    val counter = rememberSaveable { mutableStateOf(0) }
    val isRunning = rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = AppSpacing.medium),
            text = AppScreen.CounterRememberCoroutineScopeScreen.title,
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
                    onClick = {
                        scope.launch {
                            isRunning.value = true
                            delay(3000) // Simulate a delay
                            counter.value++
                            isRunning.value = false
                        }
                    },
                    enabled = !isRunning.value
                ) {
                    Text(if (isRunning.value) "Waiting..." else "Add +1 (3s delay)")
                }
            }

            Spacer(
                modifier = Modifier.height(AppSpacing.medium)
                    .weight(1f)
            )

            InfoCard(
                modifier = Modifier.padding(AppSpacing.small),
                title = "How does rememberCoroutineScope work?",
                message = "It provides a CoroutineScope bound to the Composable's lifecycle, ideal for event-driven tasks like button clicks.",
                points = listOf(
                    "Automatically cancelled when the Composable is removed from the UI",
                    "Essential for calling 'suspend' functions (like delay or snackbars) from events",
                    "Prevents memory leaks by cleaning up ongoing jobs automatically",
                    "Loses its state and running coroutines on configuration changes (e.g., rotation)"
                ),
                highlight = "Use for UI-related jobs; prefer ViewModelScope for business logic."
            )
        }

    }
}