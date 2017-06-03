package za.co.riggaroo.datecountdown.ui.event.list

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import za.co.riggaroo.datecountdown.CountdownApplication
import za.co.riggaroo.datecountdown.R
import za.co.riggaroo.datecountdown.entity.Event
import za.co.riggaroo.datecountdown.injection.CountdownFactory
import za.co.riggaroo.datecountdown.ui.event.add.AddEventActivity
import java.util.*

class EventListFragment : LifecycleFragment() {
    private lateinit var adapter: EventAdapter
    private lateinit var eventListViewModel: EventListViewModel

    private val deleteClickListener: View.OnClickListener = View.OnClickListener { v ->

        eventListViewModel.deleteEvent(v.tag as Event)
    }

    private val itemClickListener = View.OnClickListener { v ->
        val (_, name) = v.tag as Event

        Toast.makeText(context, "Clicked:" + name, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_list_events, container, false)
        setupRecyclerView(v)

        val floatingActionButton = v.findViewById(R.id.fab_add) as FloatingActionButton
        floatingActionButton.setOnClickListener { _ -> startActivity(Intent(context, AddEventActivity::class.java)) }

        val application = activity.application as CountdownApplication
        eventListViewModel = ViewModelProviders.of(this, CountdownFactory(application)).get(EventListViewModel::class.java)
        eventListViewModel.events.observe(this, Observer { events: List<Event>? ->
            Log.d(TAG, "Events Changed:" + events)
            adapter.items = events!!
        })

        return v
    }

    private fun setupRecyclerView(v: View) {
        val recyclerView = v.findViewById(R.id.recycler_view_list_events) as RecyclerView
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        adapter = EventAdapter(ArrayList<Event>(), context, itemClickListener, deleteClickListener)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {
        private val TAG = "EventListFragment"
    }

}

