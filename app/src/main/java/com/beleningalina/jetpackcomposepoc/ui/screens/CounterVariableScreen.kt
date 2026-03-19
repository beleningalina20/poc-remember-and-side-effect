package com.beleningalina.jetpackcomposepoc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.beleningalina.jetpackcomposepoc.ui.components.StateWarningCard
import com.beleningalina.jetpackcomposepoc.ui.theme.Colors

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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.SurfaceWhite)
            .padding(16.dp)
    ) {
        val (counterContent, warningCard) = createRefs()

        Column(
            modifier = Modifier.fillMaxSize()
                .constrainAs(counterContent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("$counter")
            Button(
                onClick = {
                    counter++
                }
            ) {
                Text("+")
            }
        }

        StateWarningCard(
            modifier = Modifier.constrainAs(warningCard) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            title ="Common Mistake: Why isn't it updating?",
            description = "This counter does NOT work as expected because:",
            reasons = listOf(
                "Without *remember*, the variable is re-initialized every time the function runs",
                "Recomposition executes the Composable function again from the top",
                "The state is created fresh (0) on every cycle",
                "The UI 'forgets' the previous value"
            ),
            summary = "Solution: Use val counter = remember { mutableStateOf(0) }"
        )
    }
}

@Composable
@Preview
fun CounterVariableScreenPreview() {
    CounterVariableScreen()
}