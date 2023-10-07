package com.example.daznapplication.ui.event_list

import com.example.daznapplication.domain.model.Event

data class EventState(
    val events: List<Event> = emptyList(),
    val error: String? = null
)