package com.beleningalina.jetpackcomposepoc.ui.screens.voiceRecorder

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.beleningalina.jetpackcomposepoc.ui.components.InfoCard
import com.beleningalina.jetpackcomposepoc.ui.components.TextField
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * This example uses rememberUpdatedState to maintain a reference to the latest value
 * inside a long-running process without restarting it.
 *
 * - rememberUpdatedState: updates the value reference so long-lived effects (like coroutines)
 * can access the most recent state even if the Composable recomposes.
 *
 * Advantage: No need to restart expensive effects or coroutines just because a value changed.
 * Disadvantage: It only updates the reference; it doesn't trigger the effect's logic again.
 */
@Composable
fun VoiceRecorderScreen() {
    val scope = rememberCoroutineScope()

    val fileName = rememberSaveable { mutableStateOf("File 1") }
    val recordingStatus = rememberSaveable { mutableStateOf("") }
    val isRecording = rememberSaveable { mutableStateOf(false) }

    val latestFileName = rememberUpdatedState(fileName.value)

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(AppSpacing.medium)
    ) {
        val (title, content, infoCard) = createRefs()

        Text(
            text = AppScreen.VoiceRecorderScreen.title,
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

        Column(
            modifier = Modifier.fillMaxSize()
                .constrainAs(content) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(infoCard.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(AppSpacing.large),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = fileName.value,
                onValueChange = { fileName.value = it },
                label = "File title"
            )

            Button(
                modifier = Modifier.padding(vertical = AppSpacing.large),
                onClick = {
                    scope.launch {
                        isRecording.value = true
                        recordingStatus.value = "🔴 Recording '${latestFileName.value}'..."
                        delay(10000)
                        recordingStatus.value = "'${latestFileName.value}' has been recorded"
                        isRecording.value = false
                    }
                },
                enabled = !isRecording.value
            ) {
                Text("Record")
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = recordingStatus.value,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }

        InfoCard(
            modifier = Modifier.constrainAs(infoCard) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            title = "What is rememberUpdatedState used for?",
            message = "It keeps the latest value or lambda reference inside a long-lived effect (SideEffect) without restarting it.",
            points = listOf(
                "No need to restart effects just because a value changed",
                "Allows long-running tasks to access the most current state",
                "Only updates the reference, not the effect itself",
                "Does not replace proper state management"
            ),
            highlight = "Use it to prevent 'stale' data in long-lived coroutines."
        )
    }
}