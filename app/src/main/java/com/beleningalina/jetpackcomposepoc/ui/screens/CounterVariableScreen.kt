package com.beleningalina.jetpackcomposepoc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.beleningalina.jetpackcomposepoc.ui.components.CounterContent
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

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(AppSpacing.medium)
    ) {
        val (title, counterContent, infoCard) = createRefs()

        Text(
            text = AppScreen.CounterVariable.title,
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

        CounterContent(
            modifier = Modifier.constrainAs(counterContent) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            counter = counter,
            onIncrementCounter = { counter++}
        )

        InfoCard(
            modifier = Modifier.constrainAs(infoCard) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
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