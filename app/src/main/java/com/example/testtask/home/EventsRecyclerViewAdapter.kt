package com.example.testtask.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Event
import com.example.testtask.databinding.ItemEventBinding

class EventsRecyclerViewAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<EventsRecyclerViewAdapter.EventHolder>() {

    interface Listener {
        fun onEventClicked(event: Event)
    }

    private var events = listOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventHolder(binding)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        val event = events[position]
        holder.bind(event, listener)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun updateData(newList: List<Event>){
        val diffCallback = DiffCallback(events, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        events = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class EventHolder(private val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event, listener: Listener) {
            binding.event = event
            binding.root.setOnClickListener {
                listener.onEventClicked(event)
            }
        }

    }

}