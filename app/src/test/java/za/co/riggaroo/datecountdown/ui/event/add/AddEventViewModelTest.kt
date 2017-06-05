package za.co.riggaroo.datecountdown.ui.event.add

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import za.co.riggaroo.datecountdown.repository.EventRepository

/**
 * @author rebeccafranks
 * *
 * @since 2017/05/09.
 */
class AddEventViewModelTest {

    lateinit var addEventViewModel: AddEventViewModel

    @Mock
    lateinit var eventRepository: EventRepository

    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        addEventViewModel = AddEventViewModel()
        addEventViewModel.eventRepository = eventRepository
    }

    @Test
    fun addEvent() {
        `when`(eventRepository.addEvent(ArgumentMatchers.any())).thenReturn(Completable.complete())

        addEventViewModel.addEvent()

        verify<EventRepository>(eventRepository).addEvent(ArgumentMatchers.any())
    }

    companion object {
        @BeforeClass
        fun setUpClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        }

        @AfterClass
        fun tearDownClass() {
            RxAndroidPlugins.reset()
        }
    }
}