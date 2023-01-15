package com.example.domain.mappers

import com.example.data.models.EventModel
import com.example.domain.entities.Event
import com.example.domain.entities.EventState
import javax.inject.Inject

class EventMapper @Inject constructor() : DaoMapper<EventModel, Event> {

    override fun fromModel(data: EventModel): Event {
        return Event(
            id = data.id,
            name = data.name,
            description = data.description,
            date = data.date,
            time = data.time,
            place = data.place,
            temperature = data.temperature,
            state = EventState.values()[data.state],
            iconUrl = data.iconUrl
        )
    }

    override fun toModel(data: Event): EventModel {
        return EventModel(
            id = data.id,
            name = data.name,
            description = data.description,
            date = data.date,
            time = data.time,
            place = data.place,
            temperature = data.temperature,
            state = data.state.ordinal,
            iconUrl = data.iconUrl
        )
    }

    override fun fromModelList(listData: List<EventModel>): List<Event> {
        val mappedList = mutableListOf<Event>()
        listData.forEach {
            mappedList.add(fromModel(it))
        }
        return mappedList
    }
}