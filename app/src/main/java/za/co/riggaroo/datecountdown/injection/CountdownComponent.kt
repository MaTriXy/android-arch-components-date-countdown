package za.co.riggaroo.datecountdown.injection

import dagger.Component
import za.co.riggaroo.datecountdown.ui.event.add.AddEventViewModel
import za.co.riggaroo.datecountdown.ui.event.list.EventListViewModel
import javax.inject.Singleton

/**
 * @author rebeccafranks
 * *
 * @since 2017/05/11.
 */
@Singleton
@Component(modules = arrayOf(CountdownModule::class))
interface CountdownComponent {

    fun inject(eventListViewModel: EventListViewModel)

    fun inject(addEventViewModel: AddEventViewModel)

    interface Injectable {
        fun inject(countdownComponent: CountdownComponent)
    }
}
