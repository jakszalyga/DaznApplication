package com.example.daznapplication.domain.use_case

import com.example.daznapplication.common.Result
import com.example.daznapplication.domain.model.ScheduledEvent
import com.example.daznapplication.domain.repository.EventRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetScheduledEventUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(): Flow<Result<List<ScheduledEvent>>> = flow {

        while (true) {
            emit(repository.getScheduledEvents())
            delay(30000)
        }
    }
}