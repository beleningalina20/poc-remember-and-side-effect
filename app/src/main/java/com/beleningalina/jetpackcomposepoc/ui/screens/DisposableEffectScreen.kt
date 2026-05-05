package com.beleningalina.jetpackcomposepoc.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun DisposableEffectScreen(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            Log.d("Welcome message", "Lifecycle event: $event")
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        Log.d("Welcome message", "Observer registered")

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            Log.d("Welcome message", "Observer unregistered")
        }
    }

    Text(
        modifier = modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        text = "Welcome to app"
    )
}