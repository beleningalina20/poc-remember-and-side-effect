package com.beleningalina.jetpackcomposepoc.ui.screens.counter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
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

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(AppSpacing.medium)
    ) {
        val (title, counterContent, infoCard) = createRefs()

        Text(
            text = AppScreen.CounterRememberScreen.title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = AppSpacing.large)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // Composable reutilizable
        Column(
            modifier = Modifier.fillMaxSize()
                .constrainAs(counterContent) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(infoCard.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
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

        InfoCard(
            modifier = Modifier.constrainAs(infoCard) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            title = "How does this counter survive rotation?",
            message = "This counter works because it uses rememberSaveable, which persists state across configuration changes.",
            points = listOf(
                "State is saved automatically in an internal Bundle",
                "You don’t need to manage the Bundle manually",
                "Works out of the box for primitive types (Int, String, Boolean)",
                "For complex objects, a custom Saver is required"
            ),
            highlight = "For scalable apps, prefer ViewModel + SavedStateHandle"
        )
    }
}