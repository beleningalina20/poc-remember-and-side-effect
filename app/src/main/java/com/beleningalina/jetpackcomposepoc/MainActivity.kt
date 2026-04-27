package com.beleningalina.jetpackcomposepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.navigation.addFeedScreenGraph
import com.beleningalina.jetpackcomposepoc.ui.theme.JetpackComposePOCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePOCTheme {
                NavigationHost()
            }
        }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.Main.route) {
        addFeedScreenGraph(navController)
    }
}

/*
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

@Composable
fun Counter(modifier: Modifier = Modifier) {
    val counter = remember { mutableStateOf(0) }
    val message = remember { mutableStateOf("") }

    LaunchedEffect(counter.value) {
        delay(3000)
        message.value = "Counter changed to ${counter.value}"
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("${counter.value}")
        Button(onClick = { counter.value++}) {
            Text("+")
        }
        Text(message.value)
    }
}

@Composable
fun Counter(
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
}*/