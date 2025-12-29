package com.beleningalina.jetpackcomposepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beleningalina.jetpackcomposepoc.ui.theme.JetpackComposePOCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePOCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Counter(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}


/**
 * This counter does NOT work as expected.
 *
 * - `counter` is a regular Int, not a State
 * - Updating a normal variable does not trigger recomposition
 * - Jetpack Compose only observes State changes
 * - As a result, the UI never updates
 *
 * Not every variable is State.
 * Use State (read-only) or MutableState (mutable) to update the UI.
 */
/*@Composable
fun Counter(modifier: Modifier = Modifier) {
    var counter: Int = 0
    Column(
        modifier = modifier.fillMaxSize(),
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
}*/

/**
 * This counter does NOT work as expected.
 *
 * A mutableState variable needs to be created using remember
 */
@Composable
fun Counter(modifier: Modifier = Modifier) {
    var counter = mutableStateOf(0)
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("$counter")
        Button(
            onClick = {
                counter.value++
            }
        ) {
            Text("+")
        }
    }
}