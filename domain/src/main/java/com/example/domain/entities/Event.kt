package com.example.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val name: String,
    val description: String,
    val date: String,
    val time: String,
    val place: String,
    val temperature: Double = 0.0,
    val iconUrl: String = "null",
    val state: EventState = EventState.DEFAULT
) : Parcelable

enum class EventState {
    DEFAULT, VISITED, MISSED
}