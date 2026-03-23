package com.beleningalina.jetpackcomposepoc.ui.screens.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver

val UserSaver = Saver<MutableState<User>, Map<String, Any>>(
    save = { state ->
        mapOf(
            "name" to state.value.name,
            "email" to state.value.email
        )
    },
    restore = { map ->
        mutableStateOf(
            User(
                name = map["name"] as String,
                email = map["email"] as String
            )
        )
    }
)