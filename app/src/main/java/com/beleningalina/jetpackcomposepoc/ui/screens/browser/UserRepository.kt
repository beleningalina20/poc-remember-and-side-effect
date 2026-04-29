package com.beleningalina.jetpackcomposepoc.ui.screens.browser

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun fetchUserData(userId: Int): String {
        // Simulating network latency (2 seconds)
        delay(2000)
        return "Details for User #$userId"
    }
}