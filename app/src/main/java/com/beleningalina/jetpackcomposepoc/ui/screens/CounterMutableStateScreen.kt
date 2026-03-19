package com.beleningalina.jetpackcomposepoc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.beleningalina.jetpackcomposepoc.ui.components.StateWarningCard
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.Colors

/**
 * This counter does NOT work as expected.
 *
 * A mutableState variable needs to be created using remember
 *
 * Without remember, the counter would reset on every recomposition
 */

@Composable
fun CounterMutableStateScreen() {
    val counter = mutableStateOf(0)

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
            Text("${counter.value}")
            Button(
                onClick = {
                    counter.value++
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
            title ="Missing Remember: Why is it resetting?",
            description = "A *mutableState* variable needs to be created using *remember* because:",
            reasons = listOf(
                "Without *remember*, the variable is re-initialized every time the function runs",
                "Recomposition executes the Composable function again from the top",
                "The state is created fresh (0) on every cycle",
                "The UI 'forgets' the previous value"
            ),
            summary = "Key: Use State or MutableState to update the UI."
        )
    }
}

@Composable
@Preview
fun CounterMutableStateScreenPreview() {
    CounterMutableStateScreen()
}