package com.example.daznapplication.domain.use_case

import com.example.daznapplication.domain.repository.EventRepository
import com.example.daznapplication.common.Result
import com.example.daznapplication.domain.model.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: EventRepository
) {
    operator fun invoke(): Flow<Result<List<Event>>> = flow {

        while (true) {
            emit(repository.getEvents())
            delay(30000)
        }
    }
}