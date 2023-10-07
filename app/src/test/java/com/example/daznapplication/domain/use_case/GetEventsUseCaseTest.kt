package com.example.daznapplication.domain.use_case

import com.example.daznapplication.domain.model.Event
import com.example.daznapplication.domain.repository.EventRepository
import kotlinx.coroutines.delay
import com.example.daznapplication.common.Result
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetEventsUseCaseTest {

    private lateinit var getEventsUseCase: GetEventsUseCase
    private lateinit var eventRepository: EventRepository

    @Before
    fun setup() {
        eventRepository = mock(EventRepository::class.java)
        getEventsUseCase = GetEventsUseCase(eventRepository)
    }

    @Test
    fun `invoke returns a flow of Result with events`() = runTest {
        val events = listOf(
            Event("", "1", "", "", "Event1", ""),
            Event("", "2", "", "", "Event2", "")
        )

        val resultSuccess = Result.Success(events)
        `when`(eventRepository.getEvents()).thenReturn(resultSuccess)

        val resultFlow = getEventsUseCase.invoke()

        val result = mutableListOf<Result<List<Event>>>()
        val job = resultFlow
            .onEach { result.add(it) }
            .launchIn(this)

        delay(1000)

        job.cancel()

        assertEquals(1, result.size)
        assertEquals(resultSuccess, result[0])
        assertEquals(events, result[0].data)
    }

    @Test
    fun `invoke returns a flow of Result with error`() = runTest {
        val resultError = Result.Error<List<Event>>("")
        `when`(eventRepository.getEvents()).thenReturn(resultError)

        val result = mutableListOf<Result<List<Event>>>()
        val job = getEventsUseCase.invoke()
            .onEach { result.add(it) }
            .launchIn(this)

        delay(1000)

        job.cancel()

        assertEquals(1, result.size)
        assertEquals(resultError, result[0])
        assertEquals(resultError.message, result[0].message)
    }
}