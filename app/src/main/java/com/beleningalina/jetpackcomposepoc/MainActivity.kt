package com.beleningalina.jetpackcomposepoc

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.beleningalina.jetpackcomposepoc.ui.theme.JetpackComposePOCTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
 * - `counter` is a regular Int variable, not a State
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
 *
 * Without remember, the counter would reset on every recomposition
 */
/*@Composable
fun Counter(modifier: Modifier = Modifier) {
    val counter = mutableStateOf(0)
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("$counter.value")
        Button(
            onClick = {
                counter.value++
            }
        ) {
            Text("+")
        }
    }
}*/

/**
 * remember
 *
 * - remember keeps the state alive during recompositions
 * - mutableStateOf triggers recomposition when the value changes
 * - does not survive configuration changes
 *
 * */
/*@Composable
fun Counter(modifier: Modifier = Modifier) {
    val counter = remember { mutableStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize(),
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
}*/

/**
 * rememberSaveable
 *
 * - remember keeps the state alive during recompositions, it survives configuration changes
 * - mutableStateOf triggers recomposition when the value changes
 *
 * */
/*@Composable
fun Counter(modifier: Modifier = Modifier) {
    val counter = rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize(),
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
}*/

/**
 * Replacing rememberSaveable by viemodel
 *
 * rememberSaveable persists UI state using a Bundle, which is limited to primitive or parcelable types.
 * ViewModel avoids this limitation by keeping state in memory, making it a better choice for complex or long-lived state.
 *
 * */
/*class CounterViewModel : ViewModel() {

    private val _counter = mutableStateOf(0)
    val counter: State<Int> = _counter

    fun increment() {
        _counter.value++
    }
}

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = viewModel()
) {

    val counter = viewModel.counter.value

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("$counter")
        Button(
            onClick = {
                viewModel.increment()
            }
        ) {
            Text("+")
        }
    }
}*/

/**
 * rememberCoroutineScope
 *
 * Responsible for managing coroutines within composable. It provides a coroutineScope that’s intrinsically tied to the composable’s lifecycle
 *
 * */

@Composable
fun Counter(modifier: Modifier = Modifier) {
    val counter = rememberSaveable { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("${counter.value}")
        Button(
            onClick = {
                scope.launch {
                    delay(3000) // Simulate a delay
                    counter.value++
                }
            }
        ) {
            Text("Increment after 3 seconds")
        }
    }
MovieDetailScreen("") { }
}


/*@Composable
fun MovieDetailScreen(
    movieId: String,
    onPlayTrailer: (String) -> Unit
) {
    LaunchedEffect(movieId) {
        delay(3000)
        onPlayTrailer(movieId) // movieId can change when coroutine is running
    }
}*/

@Composable
fun MovieDetailScreen(
    movieId: String,
    onPlayTrailer: (String) -> Unit
) {
    val currentOnPlayTrailer = rememberUpdatedState(onPlayTrailer)
    val currentMovieId = rememberUpdatedState(movieId)

    LaunchedEffect(currentMovieId.value) {
        delay(3000)
        currentOnPlayTrailer.value(currentMovieId.value)
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier) {
    val counter = remember { mutableStateOf(0) }

    SideEffect {
        Log.d("CounterScreen", "Counter value = ${counter.value}")
    }

    Column(
        modifier = modifier.fillMaxSize(),
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
}