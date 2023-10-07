package com.example.daznapplication.ui.event_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daznapplication.common.Result
import com.example.daznapplication.domain.use_case.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListFragmentViewModel @Inject constructor(
    private val getEventUseCase: GetEventsUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(EventState())
    val state: Flow<EventState> = _state

    init {
        getEventsFlow()
    }

    private fun getEventsFlow() {
        viewModelScope.launch {
            getEventUseCase.invoke().collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = EventState(events = result.data ?: emptyList())
                    }

                    is Result.Error -> {
                        Log.d(
                            EventListFragmentViewModel::javaClass.name,
                            "Error while fetching events: ${result.message}"
                        )

                    }

                    is Result.Loading -> {
                        Log.d(EventListFragmentViewModel::javaClass.name, "Fetching events data")

                    }
                }
            }
        }
    }
}