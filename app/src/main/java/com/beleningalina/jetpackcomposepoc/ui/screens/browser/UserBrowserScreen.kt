package com.beleningalina.jetpackcomposepoc.ui.screens.browser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.beleningalina.jetpackcomposepoc.ui.components.InfoCard
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing

/**
 * This example uses LaunchedEffect to automatically trigger a background task (like fetching data)
 * when the screen loads, and restarts it whenever the currentUserId changes.
 *
 * - LaunchedEffect: automatically runs a coroutine when it enters the composition.
 * - key1 (currentUserId): If this value changes, the ongoing coroutine is cancelled and restarted from the beginning.
 *
 * Advantage: It is very safe for network calls. It prevents duplicate or old network calls
 * by cancelling previous tasks if the key changes quickly (e.g., fast clicks).
 * Limitation: It is UI-driven and runs automatically. It cannot be used directly inside
 * user events like onClick (use rememberCoroutineScope for that).
 */
@Composable
fun UserBrowserScreen() {
    val repository = remember { UserRepository() }
    val currentUserId = remember { mutableStateOf(1) }

    val isLoading = remember { mutableStateOf(true) }
    val userData = remember { mutableStateOf("") }

    LaunchedEffect(key1 = currentUserId.value) {
        isLoading.value = true
        userData.value = repository.fetchUserData(currentUserId.value)
        isLoading.value = false
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(AppSpacing.medium)
    ) {
        val (title, content, infoCard) = createRefs()

        Text(
            text = AppScreen.UserBrowserScreen.title,
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
                .padding(AppSpacing.medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Currently viewing User ID: ${currentUserId.value}")

            Spacer(modifier = Modifier.height(AppSpacing.medium))

            if (isLoading.value) {
                Text(text = "Downloading data from server... ⏳")
            } else {
                Text(text = "✅ Data Loaded: ${userData.value}")
            }

            Spacer(modifier = Modifier.height(AppSpacing.medium))

            Button(
                onClick = { currentUserId.value++ },
                enabled = !isLoading.value
            ) {
                Text(text = "Load Next User")
            }
        }

        InfoCard(
            modifier = Modifier.constrainAs(infoCard) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            title = "What is LaunchedEffect used for?",
            message = "It automatically launches a coroutine when the Composable opens or when its key changes.",
            points = listOf(
                "Starts automatically when entering the screen (e.g., initial data load)",
                "Cancels and restarts the task if the provided 'key' changes",
                "Automatically cancelled when the Composable leaves the UI",
                "Prevents overlapping tasks (stops old tasks if the key changes too fast)"
            ),
            highlight = "Use for automatic UI tasks; prefer rememberCoroutineScope for button clicks."
        )
    }
}