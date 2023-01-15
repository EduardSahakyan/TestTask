package com.example.testtask.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Resource
import com.example.domain.entities.Event
import com.example.domain.entities.EventState
import com.example.domain.usecases.AddEventUseCase
import com.example.domain.usecases.GetWeatherUseCase
import com.example.domain.usecases.RemoveEventUseCase
import com.example.testtask.common.AppDispatchers
import com.example.testtask.common.BaseViewModel
import com.example.testtask.utils.HTTPS
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val addEventUseCase: AddEventUseCase,
    private val removeEventUseCase: RemoveEventUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val _insertedEvent = SingleLiveEvent<Unit>()
    val insertedEvent: LiveData<Unit> = _insertedEvent

    private val _errorEvent = SingleLiveEvent<String>()
    val errorEvent: LiveData<String> = _errorEvent

    fun isFieldsCorrect(name: String, description: String, date: String, time: String, place: String, id: Int, state: EventState) {
        if (name.isEmpty()) return
        if (description.isEmpty()) return
        if (place.isEmpty()) return
        if (date.isEmpty()) return
        if (time.isEmpty()) return
        val event = Event(
            id = id,
            name = name,
            description = description,
            date = date,
            time = time,
            place = place,
            state = state
        )
        getWeather(event)
    }

    fun removeEvent(id: Int) {
        viewModelScope.launch(dispatchers.ioDispatcher) {
            removeEventUseCase(id)
        }
    }

    private fun getWeather(event: Event) {
        getWeatherUseCase(event.place, event.date)
            .onEach {
                when (it) {
                    is Resource.Error -> {
                        addEventUseCase(event)
                        withContext(dispatchers.mainDispatcher) {
                            _insertedEvent.value = Unit
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
                        withContext(dispatchers.mainDispatcher) {
                            _insertedEvent.value = Unit
                        }
                    }
                }
            }
            .flowOn(dispatchers.ioDispatcher)
            .launchIn(viewModelScope)
    }

}