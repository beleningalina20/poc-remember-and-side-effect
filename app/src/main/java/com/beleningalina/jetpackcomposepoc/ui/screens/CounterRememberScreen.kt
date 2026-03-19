package com.beleningalina.jetpackcomposepoc.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun CounterRememberScreen() {

}

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
