package com.example.daznapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.daznapplication.DaznApplication
import com.example.daznapplication.databinding.CardViewEventBinding
import com.example.daznapplication.domain.model.ScheduledEvent
import com.example.daznapplication.utils.DateUtils
import com.squareup.picasso.Picasso
import java.time.LocalDateTime


class ScheduledEventAdapter :
    ListAdapter<ScheduledEvent, ScheduledEventAdapter.ScheduledEventsViewHolder>(
        DiffUtilCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduledEventsViewHolder {
        val binding =
            CardViewEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduledEventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduledEventsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ScheduledEventsViewHolder(private val binding: CardViewEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            event: ScheduledEvent,
        ) {
            binding.apply {
                eventTitle.text = event.subtitle
                eventSubtitle.text = event.title
                eventDate.text = DateUtils.transformDate(event.date, LocalDateTime.now())
                Picasso.with(DaznApplication.appContext).load(event.imageUrl).into(eventPoster)
            }
        }
    }

    fun setList(list: List<ScheduledEvent>) {
        val updatedList =
            list.filter { DateUtils.isTomorrow(it.date, LocalDateTime.now()) }
                .sortedBy { it.date }
        submitList(updatedList)
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<ScheduledEvent>() {
        override fun areItemsTheSame(
            oldItem: ScheduledEvent,
            newItem: ScheduledEvent,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ScheduledEvent,
            newItem: ScheduledEvent,
        ): Boolean {
            return oldItem == newItem
        }
    }
}