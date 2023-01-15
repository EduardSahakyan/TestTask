package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.EventDao
import com.example.data.models.EventModel

@Database(entities = [EventModel::class], version = 1, exportSchema = false)
abstract class EventDatabase: RoomDatabase() {

    abstract fun eventDao(): EventDao

}