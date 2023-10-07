package com.example.daznapplication.utils.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun LifecycleOwner.launchAndRepeatOn(
    state: Lifecycle.State,
    block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state, block)
    }
}

fun LifecycleOwner.launchAndRepeatWhenStarted(block: suspend CoroutineScope.() -> Unit) {
    launchAndRepeatOn(Lifecycle.State.STARTED, block)
}