package za.co.riggaroo.datecountdown.injection

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import za.co.riggaroo.datecountdown.CountdownApplication
import za.co.riggaroo.datecountdown.db.EventDatabase
import za.co.riggaroo.datecountdown.repository.EventRepository
import za.co.riggaroo.datecountdown.repository.EventRepositoryImpl
import javax.inject.Singleton

/**
 * @author rebeccafranks
 * *
 * @since 2017/05/12.
 */
@Module
class MockCountdownModule(val application: CountdownApplication) {

    val applicationContext: Context
        @Provides
        get() = application

    @Provides
    @Singleton
    fun provideEventDatabase(context: Context): EventDatabase {
        return Room.inMemoryDatabaseBuilder(context.applicationContext, EventDatabase::class.java).build()
    }

    @Provides
    @Singleton
    fun providesEventRepository(eventDatabase: EventDatabase): EventRepository {
        return EventRepositoryImpl(eventDatabase)
    }

}
