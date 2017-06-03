package za.co.riggaroo.datecountdown.ui.event.add

import android.arch.lifecycle.ViewModel
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDateTime
import timber.log.Timber
import za.co.riggaroo.datecountdown.entity.Event
import za.co.riggaroo.datecountdown.injection.CountdownComponent
import za.co.riggaroo.datecountdown.repository.EventRepository
import javax.inject.Inject


class AddEventViewModel : ViewModel(), CountdownComponent.Injectable {

    @Inject
    lateinit var eventRepository: EventRepository
    var eventName = String()
    var eventDescription = String()

    var eventDateTime = LocalDateTime.now()


    fun addEvent() {
        val event = Event(0, eventName, eventDescription, eventDateTime)
        eventRepository.addEvent(event).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onComplete() {
                        Timber.d("onComplete - successfully added event")
                    }

                    override fun onError(e: Throwable) {
                        Timber.d("onError - add:", e)
                    }
                })
    }


    override fun inject(countdownComponent: CountdownComponent) {
        countdownComponent.inject(this)
    }
}
