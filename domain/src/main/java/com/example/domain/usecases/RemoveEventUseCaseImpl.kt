package com.example.domain.usecases

import com.example.data.repositories.EventRepository
import com.example.domain.entities.Event
import com.example.domain.mappers.EventMapper
import javax.inject.Inject

class RemoveEventUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository,
    private val eventMapper: EventMapper
) : RemoveEventUseCase {

    override suspend fun invoke(event: Event) {
        eventRepository.removeEvent(eventMapper.toModel(event))
    }

    override suspend fun invoke(id: Int) {
        eventRepository.removeEvent(id)
    }
}