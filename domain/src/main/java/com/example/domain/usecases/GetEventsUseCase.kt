package com.example.domain.usecases

import com.example.domain.entities.Event
import kotlinx.coroutines.flow.Flow

interface GetEventsUseCase {

    operator fun invoke(): Flow<List<Event>>
}