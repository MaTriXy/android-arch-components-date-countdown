package za.co.riggaroo.datecountdown.ui.event.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import za.co.riggaroo.datecountdown.R
import za.co.riggaroo.datecountdown.entity.Event

class EventAdapter internal constructor(items: List<Event>, private val context: Context, private val viewClickListener: View.OnClickListener, private val deleteClickListener: View.OnClickListener) : RecyclerView.Adapter<EventViewHolder>() {

    var items: List<Event> = items
        set(newItems) {
            field = newItems
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): EventViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_event, parent, false)
        return EventViewHolder(v)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = items[position]
        holder.eventTextView.text = item.name
        holder.descriptionTextView.text = item.description
        holder.countdownTextView.text = context.getString(R.string.days_until, item.getDaysBetween())
        holder.itemView.tag = item
        holder.deleteButton.tag = item
        holder.deleteButton.setOnClickListener(deleteClickListener)
        holder.itemView.setOnClickListener(viewClickListener)
    }

    override fun getItemCount() = items.size

}