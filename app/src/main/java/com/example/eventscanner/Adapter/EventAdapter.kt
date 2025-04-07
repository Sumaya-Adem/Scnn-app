package com.example.eventscanner.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventscanner.Model.EventsModel
import com.example.eventscanner.R

class EventAdapter(private val eventList: MutableList<EventsModel>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>()  {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventImage: ImageView = itemView.findViewById(R.id.event_image)
        val eventTitle: TextView = itemView.findViewById(R.id.event_title)
        val eventDate: TextView = itemView.findViewById(R.id.event_date)
        val eventTime: TextView = itemView.findViewById(R.id.time)
        val eventLocation: TextView = itemView.findViewById(R.id.location)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventAdapter.EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        val event = eventList[position]

        holder.eventTitle.text = event.title
        holder.eventDate.text = event.date
        holder.eventTime.text = event.time
        holder.eventLocation.text = event.location

// Load image using Glide
        Glide.with(holder.itemView.context)
            .load(event.image)  // If it's a drawable resource (Int)
            .into(holder.eventImage)
    }


    override fun getItemCount(): Int {
        return eventList.size
    }

    fun removeItem(position: Int) {
        eventList.removeAt(position)
        notifyItemRemoved(position)
    }
}