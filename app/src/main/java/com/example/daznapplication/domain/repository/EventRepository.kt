package com.example.daznapplication.domain.repository

import com.example.daznapplication.common.Result
import com.example.daznapplication.domain.model.Event
import com.example.daznapplication.domain.model.ScheduledEvent

interface EventRepository {

    suspend fun getEvents(): Result<List<Event>>

    suspend fun getScheduledEvents(): Result<List<ScheduledEvent>>
}