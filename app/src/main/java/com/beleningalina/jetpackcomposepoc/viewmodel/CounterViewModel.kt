package com.beleningalina.jetpackcomposepoc.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class CounterViewModel : ViewModel() {
    private val _counter: MutableState<Int> = mutableStateOf(0)
    val counter: State<Int> = _counter

    fun increment() {
        _counter.value++
    }
}

