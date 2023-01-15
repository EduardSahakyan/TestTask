package com.example.domain.usecases

import com.example.data.repositories.EventRepository
import com.example.domain.mappers.EventMapper
import com.example.domain.entities.Event
import javax.inject.Inject

class AddEventUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository,
    private val eventMapper: EventMapper
) : AddEventUseCase {

    override suspend fun invoke(event: Event) {
        eventRepository.addEvent(eventMapper.toModel(event))
    }
}