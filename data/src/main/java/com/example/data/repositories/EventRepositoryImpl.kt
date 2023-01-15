package com.example.data.repositories

import com.example.data.datasources.local.EventLocalDataSource
import com.example.data.models.EventModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class EventRepositoryImpl @Inject constructor(
    private val eventLocalDataSource: EventLocalDataSource
) : EventRepository {

    override suspend fun addEvent(event: EventModel) {
        eventLocalDataSource.addEvent(event)
    }

    override suspend fun removeEvent(event: EventModel) {
        eventLocalDataSource.removeEvent(event)
    }

    override suspend fun removeEvent(id: Int) {
        eventLocalDataSource.removeEvent(id)
    }

    override fun getEvents(): Flow<List<EventModel>> {
        return eventLocalDataSource.getEvents()
    }

    override suspend fun clearEvents() {
        eventLocalDataSource.clearEvents()
    }
}