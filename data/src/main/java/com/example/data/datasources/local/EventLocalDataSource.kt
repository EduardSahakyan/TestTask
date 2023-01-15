package com.example.data.datasources.local

import com.example.data.models.EventModel
import kotlinx.coroutines.flow.Flow

interface EventLocalDataSource {

    suspend fun addEvent(event: EventModel)

    suspend fun removeEvent(event: EventModel)

    suspend fun removeEvent(id: Int)

    fun getEvents(): Flow<List<EventModel>>

    suspend fun clearEvents()

}