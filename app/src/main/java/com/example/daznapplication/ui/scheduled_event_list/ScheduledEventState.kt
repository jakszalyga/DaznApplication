package com.example.daznapplication.ui.scheduled_event_list

import com.example.daznapplication.domain.model.ScheduledEvent

data class ScheduledEventState(
    val events: List<ScheduledEvent> = emptyList(),
    val error: String? = null
)