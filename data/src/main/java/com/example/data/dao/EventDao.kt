package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.data.models.EventModel
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM events")
    fun getEvents(): Flow<List<EventModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventModel)

    @Query("DELETE FROM events WHERE id =:id")
    suspend fun deleteEvent(id: Int)

    @Delete
    suspend fun deleteEvent(event: EventModel)

    @Query("DELETE FROM events")
    suspend fun clearEvents()

}

