package com.example.daznapplication.ui.scheduled_event_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daznapplication.common.Result
import com.example.daznapplication.domain.use_case.GetScheduledEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleFragmentViewModel @Inject constructor(
    private val getScheduledEventUseCase: GetScheduledEventUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(ScheduledEventState())
    val state: Flow<ScheduledEventState> = _state

    init {
        getEventsFlow()
    }

    private fun getEventsFlow() {
        viewModelScope.launch {
            getScheduledEventUseCase.invoke().collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = ScheduledEventState(events = result.data ?: emptyList())
                    }

                    is Result.Error -> {
                        Log.d(
                            ScheduleFragmentViewModel::javaClass.name,
                            "Error while fetching events: ${result.message}"
                        )

                    }

                    is Result.Loading -> {
                        Log.d(ScheduleFragmentViewModel::javaClass.name, "Fetching events data")

                    }
                }
            }
        }
    }
}