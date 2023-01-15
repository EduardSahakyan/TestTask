package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.dao.EventDao
import com.example.data.database.EventDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalModule {

    private var INSTANCE : EventDatabase? = null
    private val LOCK = Any()

    private fun provideEventsDatabase(context: Context): EventDatabase {
        INSTANCE?.let {
            return it
        }
        synchronized(LOCK){
            INSTANCE?.let {
                return it
            }
            val db = Room.databaseBuilder(context, EventDatabase::class.java, "events")
                .build()
            INSTANCE = db
            return db
        }
    }

    @Provides
    @Singleton
    internal fun provideEventDao(context: Context): EventDao {
        return provideEventsDatabase(context).eventDao()
    }

}