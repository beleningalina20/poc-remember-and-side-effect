package com.beleningalina.jetpackcomposepoc.ui.screens.product

import android.util.Log
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.beleningalina.jetpackcomposepoc.ui.components.InfoCard
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing

/**
 * This example uses SideEffect to synchronize the Compose state with an external
 * system (MockAnalytics) after every successful recomposition.
 *
 * - SideEffect: runs its block only after the Composable successfully finishes rendering.
 * - analytics.logScreenView: ensures that the analytics service always knows exactly
 * what product name is being displayed to the user.
 *
 * Advantage: It guarantees that the external system is an exact "mirror" of the UI state.
 * Disadvantage: It executes on every recomposition. It should only be used for
 * lightweight, non-suspending tasks that don't directly modify the Compose state.
 */
@Composable
fun ProductInfoScreen() {
    val productCount = remember { mutableStateOf(1) } // This variable is used for triggering recompositions
    val productName = "Smartphone Model X-${productCount.value}"
    val analytics = remember { MockAnalytics() }

    SideEffect {
        analytics.logScreenView(productName)
        Log.d("Performance", "✅ Render finalized for: $productName")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = AppSpacing.medium),
            text = AppScreen.ProductInfoScreen.title,
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
                Text(text = productName)

                Button(onClick = { productCount.value++ }) {
                    Text("Switch to Next Product")
                }
            }

            Spacer(
                modifier = Modifier.height(AppSpacing.medium)
                    .weight(1f)
            )

            InfoCard(
                modifier = Modifier.padding(AppSpacing.small),
                title = "What is SideEffect used for?",
                message = "It is used to run code whenever the Composable function is re-composed, ensuring it runs after a successful rendering.",
                points = listOf(
                    "Ideal for triggering logging or reporting analytics",
                    "Useful for informing a non-Compose system that something changed",
                    "Runs after every successful recomposition",
                    "Does not support coroutines (non-suspending only)",
                    "Not cancellable — it is a 'fire-and-forget' mechanism"
                ),
                highlight = "Use for lightweight UI-related actions; avoid heavy tasks or state modifications here."
            )
        }

    }
}