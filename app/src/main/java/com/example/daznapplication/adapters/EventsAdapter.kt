package com.example.daznapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.daznapplication.DaznApplication
import com.example.daznapplication.domain.model.Event
import com.example.daznapplication.databinding.CardViewEventBinding
import com.example.daznapplication.utils.DateUtils
import com.squareup.picasso.Picasso
import java.time.LocalDateTime


class EventsAdapter :
    ListAdapter<Event, EventsAdapter.EventsViewHolder>(
        DiffUtilCallback
    ) {
    var eventClickListener: ((Event) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding =
            CardViewEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(getItem(position), eventClickListener)
    }

    class EventsViewHolder(private val binding: CardViewEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            event: Event,
            eventClickListener: ((Event) -> Unit)?,
        ) {


            binding.apply {
                eventTitle.text = event.subtitle
                eventSubtitle.text = event.title
                eventDate.text = DateUtils.transformDate(event.date, LocalDateTime.now())

                Picasso.with(DaznApplication.appContext).load(event.imageUrl).into(eventPoster)

                root.setOnClickListener {
                    eventClickListener?.invoke(event)
                }
            }
        }

    }

    fun setList(list: List<Event>) {
        val sortedList = list.sortedBy { it.date }
        submitList(sortedList)
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(
            oldItem: Event,
            newItem: Event,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Event,
            newItem: Event,
        ): Boolean {
            return oldItem == newItem
        }
    }
}