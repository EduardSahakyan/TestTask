package com.example.domain.usecases

import com.example.data.repositories.EventRepository
import com.example.domain.mappers.EventMapper
import com.example.domain.entities.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEventsUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository,
    private val eventMapper: EventMapper
) : GetEventsUseCase {

    override fun invoke(): Flow<List<Event>> {
        return eventRepository.getEvents().map {
            eventMapper.fromModelList(it)
        }
    }

}