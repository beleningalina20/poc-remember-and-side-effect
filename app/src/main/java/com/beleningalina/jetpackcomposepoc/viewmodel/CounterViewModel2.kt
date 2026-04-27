package com.beleningalina.jetpackcomposepoc.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class CounterViewModel2(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val KEY_COUNT = "count"
    }

    val counter: StateFlow<Int> = savedStateHandle.getStateFlow(KEY_COUNT, 0)

    fun increment() {
        val newValue = (savedStateHandle[KEY_COUNT] ?: 0) + 1
        savedStateHandle[KEY_COUNT] = newValue
    }
}