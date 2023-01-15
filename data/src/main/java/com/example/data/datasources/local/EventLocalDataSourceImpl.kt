package com.example.data.datasources.local

import com.example.data.dao.EventDao
import com.example.data.models.EventModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class EventLocalDataSourceImpl @Inject constructor(
    private val eventDao: EventDao
): EventLocalDataSource {

    override suspend fun addEvent(event: EventModel) {
        eventDao.insertEvent(event)
    }

    override suspend fun removeEvent(event: EventModel) {
        eventDao.deleteEvent(event)
    }

    override suspend fun removeEvent(id: Int) {
        eventDao.deleteEvent(id)
    }

    override fun getEvents(): Flow<List<EventModel>> {
        return eventDao.getEvents()
    }

    override suspend fun clearEvents() {
        eventDao.clearEvents()
    }
}