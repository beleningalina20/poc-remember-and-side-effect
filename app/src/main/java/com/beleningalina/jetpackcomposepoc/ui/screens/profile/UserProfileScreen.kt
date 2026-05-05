package com.beleningalina.jetpackcomposepoc.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.beleningalina.jetpackcomposepoc.ui.components.InfoCard
import com.beleningalina.jetpackcomposepoc.ui.components.TextField
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing

@Composable
fun UserProfileScreen() {
    val profile = rememberSaveable(
        saver = UserSaver
    ) {
        mutableStateOf(User("", ""))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = AppSpacing.medium),
            text = AppScreen.UserProfileScreen.title,
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
                TextField(
                    value = profile.value.name,
                    onValueChange = {
                        profile.value = profile.value.copy(name = it)
                    },
                    label = "Name"
                )
                Spacer(modifier = Modifier.height(AppSpacing.small))
                TextField(
                    value = profile.value.email,
                    onValueChange = {
                        profile.value = profile.value.copy(email = it)
                    },
                    label = "Email"
                )
                Spacer(modifier = Modifier.height(AppSpacing.small))
                Text("Preview: name:\"${profile.value.name}\" - email: \"${profile.value.email}\"")
            }

            Spacer(
                modifier = Modifier.height(AppSpacing.medium)
                    .weight(1f)
            )

            InfoCard(
                modifier = Modifier.padding(AppSpacing.small),
                title = "Why does this form keep its data?",
                message = "rememberSaveable uses a custom Saver to persist complex UI state.",
                points = listOf(
                    "State is saved in an internal Android Bundle",
                    "No manual state handling is required",
                    "User is not a primitive type, so a Saver is needed",
                    "Saver defines how to convert the object to and from Bundle data"
                ),
                highlight = "For complex apps, use ViewModel"
            )

        }
    }
}