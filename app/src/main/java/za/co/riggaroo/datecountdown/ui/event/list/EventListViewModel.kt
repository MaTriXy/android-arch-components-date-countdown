package za.co.riggaroo.datecountdown.ui.event.list


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import za.co.riggaroo.datecountdown.entity.Event
import za.co.riggaroo.datecountdown.injection.CountdownComponent
import za.co.riggaroo.datecountdown.repository.EventRepository
import javax.inject.Inject

class EventListViewModel : ViewModel(), CountdownComponent.Injectable {

    @Inject
    lateinit var eventRepository: EventRepository

    var events: LiveData<List<Event>> = MutableLiveData()
        private set

    override fun inject(countdownComponent: CountdownComponent) {
        countdownComponent.inject(this)
        events = eventRepository.events
    }

    fun deleteEvent(event: Event) {
        eventRepository.deleteEvent(event)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onComplete() {
                        Timber.d("onComplete - deleted event")
                    }

                    override fun onError(e: Throwable) {
                        Timber.e("OnError - deleted event: ", e)
                    }
                })
    }

}
