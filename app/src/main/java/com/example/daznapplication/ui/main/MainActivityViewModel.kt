package com.example.daznapplication.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivityViewModel : ViewModel() {

    var currentVideoUrl: String? = null

    private val _stateFlow = MutableStateFlow(MainActivityStates.INIT)
    var stateFlow = _stateFlow

    fun onEvent(event: MainActivityEvent) {
        when (event) {
            MainActivityEvent.DismissVideoView -> _stateFlow.value =
                MainActivityStates.DISMISSING_VIDEO_VIEW

            is MainActivityEvent.ToggleActionBar -> {
                _stateFlow.value = MainActivityStates.INIT
                _stateFlow.value = MainActivityStates.TOGGLING_ACTION_BAR
            }

            is MainActivityEvent.ShowVideoView -> {
                _stateFlow.value = MainActivityStates.SHOWING_VIDEO_VIEW
                currentVideoUrl = event.videoUrl
            }
        }
    }
}