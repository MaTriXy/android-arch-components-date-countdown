package za.co.riggaroo.datecountdown.repository


import android.arch.lifecycle.LiveData

import io.reactivex.Completable
import za.co.riggaroo.datecountdown.entity.Event

/**
 * @author rebeccafranks
 * *
 * @since 2017/04/21.
 */
interface EventRepository {

    fun addEvent(event: Event): Completable

    val events: LiveData<List<Event>>

    fun deleteEvent(event: Event): Completable


}
