package com.example.daznapplication.ui.main


sealed interface MainActivityEvent {
    data object DismissVideoView : MainActivityEvent
    data class ShowVideoView(val videoUrl: String) : MainActivityEvent
    data object ToggleActionBar : MainActivityEvent
}