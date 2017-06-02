package za.co.riggaroo.datecountdown.repository

import android.arch.lifecycle.LiveData
import io.reactivex.Completable
import org.threeten.bp.LocalDateTime
import za.co.riggaroo.datecountdown.db.EventDatabase
import za.co.riggaroo.datecountdown.entity.Event
import javax.inject.Inject

/**
 * @author rebeccafranks
 * *
 * @since 2017/04/21.
 */
class EventRepositoryImpl public @Inject constructor(
        var eventDatabase: EventDatabase) : EventRepository {

    override fun addEvent(event: Event): Completable {
        return Completable.fromAction { eventDatabase.eventDao().addEvent(event) }
    }

    override //Here is where we would do more complex logic, like getting events from a cache
            //then inserting into the database etc. In this example we just go straight to the dao.
    val events: LiveData<List<Event>>
        get() = eventDatabase.eventDao().getEvents(LocalDateTime.now())

    override fun deleteEvent(event: Event): Completable {
        return Completable.fromAction { eventDatabase.eventDao().deleteEvent(event) }
    }


}
