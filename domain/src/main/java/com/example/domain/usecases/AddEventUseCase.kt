package com.example.domain.usecases

import com.example.domain.entities.Event

interface AddEventUseCase {

    suspend operator fun invoke(event: Event)
}