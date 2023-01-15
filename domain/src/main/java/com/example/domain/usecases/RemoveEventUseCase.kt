package com.example.domain.usecases

import com.example.domain.entities.Event

interface RemoveEventUseCase {

    suspend operator fun invoke(event: Event)

    suspend operator fun invoke(id: Int)
}