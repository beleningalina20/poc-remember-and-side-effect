package com.beleningalina.jetpackcomposepoc.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing
import com.beleningalina.jetpackcomposepoc.viewmodel.CounterViewModel

/**
 * Replacing rememberSaveable by viemodel
 *
 * rememberSaveable persists UI state using a Bundle, which is limited to primitive or parcelable types.
 * ViewModel avoids this limitation by keeping state in memory, making it a better choice for complex or long-lived state.
 *
 * */

@Composable
fun CounterViewModelScreen(viewModel: CounterViewModel = viewModel()) {
    val counter = viewModel.counter

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(AppSpacing.medium)
    ) {
        val (title, counterContent, infoCard) = createRefs()

        Text(
            text = AppScreen.CounterViewModelScreen.title,
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
                onClick = { viewModel.increment() }
            ) {
                Text("+")
            }
        }
    }
}


// 📌 Problema:

// Si giras la pantalla → OK (ViewModel lo mantiene)
// Si Android mata el proceso → ❌ se reinicia a 0

// Aquí el estado se puede restaurar incluso tras muerte del proceso