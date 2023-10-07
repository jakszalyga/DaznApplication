package com.example.daznapplication.data

import com.example.daznapplication.domain.model.Event
import com.example.daznapplication.domain.model.ScheduledEvent
import retrofit2.http.GET

interface EventApi {
    @GET("getSchedule")
    suspend fun getSchedule(): List<ScheduledEvent>

    @GET("getEvents")
    suspend fun getEvents(): List<Event>
}