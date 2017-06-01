package za.co.riggaroo.datecountdown.injection

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import za.co.riggaroo.datecountdown.CountdownApplication
import za.co.riggaroo.datecountdown.db.EventDatabase
import za.co.riggaroo.datecountdown.repository.EventRepositoryImpl
import javax.inject.Singleton

/**
 * @author rebeccafranks
 * *
 * @since 2017/05/11.
 */
@Module
class CountdownModule(private val countdownApplication: CountdownApplication) {

    @Provides
    fun applicationContext() = countdownApplication


    @Provides
    @Singleton
    fun providesEventRepository(eventDatabase: EventDatabase) = EventRepositoryImpl(eventDatabase)

    @Provides
    @Singleton
    fun providesEventDatabase(context: Context): EventDatabase = Room.databaseBuilder(context.applicationContext, EventDatabase::class.java, "event_db").build()

}
