package com.example.daznapplication.data

import com.example.daznapplication.domain.repository.EventRepository
import com.example.daznapplication.common.Result
import com.example.daznapplication.domain.model.Event
import com.example.daznapplication.domain.model.ScheduledEvent
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val api: EventApi
) : EventRepository {
    override suspend fun getEvents(): Result<List<Event>> {
        return try {
            val events = api.getEvents()
            (Result.Success(events))
        } catch (e: HttpException) {
            (Result.Error(e.localizedMessage ?: "Unknown error"))

        } catch (e: IOException) {
            (Result.Error("IOException"))
        }
    }

    override suspend fun getScheduledEvents(): Result<List<ScheduledEvent>> {
        return try {
            val events = api.getSchedule()
            (Result.Success(events))
        } catch (e: HttpException) {
            (Result.Error(e.localizedMessage ?: "Unknown error"))

        } catch (e: IOException) {
            (Result.Error("IOException"))
        }
    }

}