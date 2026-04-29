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
-----------------------
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