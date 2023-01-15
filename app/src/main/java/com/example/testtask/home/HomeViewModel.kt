package com.example.testtask.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Resource
import com.example.domain.entities.Event
import com.example.domain.usecases.AddEventUseCase
import com.example.domain.usecases.GetEventsUseCase
import com.example.domain.usecases.GetWeatherUseCase
import com.example.testtask.common.AppDispatchers
import com.example.testtask.common.BaseViewModel
import com.example.testtask.event.SingleLiveEvent
import com.example.testtask.utils.HTTPS
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val addEventUseCase: AddEventUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    private val _errorEvent = SingleLiveEvent<String>()
    val errorEvent: LiveData<String> = _errorEvent

    fun getEvents() {
        getEventsUseCase()
            .flowOn(dispatchers.ioDispatcher)
            .onEach {
                _events.value = it
            }
            .flowOn(dispatchers.mainDispatcher)
            .launchIn(viewModelScope)
    }

    fun updateWeather() {
        events.value?.forEach { event ->
            getWeatherUseCase(event.place, event.date)
                .onEach {
                    when (it) {
                        is Resource.Error -> {
                            withContext(dispatchers.mainDispatcher) {
                                _errorEvent.value = event.name
                            }
                        }
                        Resource.Loading -> Unit
                        is Resource.Success -> {
                            val weather = it.model
                            val iconUrl = weather.iconUrl
                            addEventUseCase(event.copy(
                                temperature = weather.temperature,
                                iconUrl = HTTPS + iconUrl.substring(2, iconUrl.length)
                            ))
                        }
                    }
                }
                .flowOn(dispatchers.ioDispatcher)
                .launchIn(viewModelScope)
        }
    }

}