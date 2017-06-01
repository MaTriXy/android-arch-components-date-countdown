package za.co.riggaroo.datecountdown.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

import za.co.riggaroo.datecountdown.dao.EventDao
import za.co.riggaroo.datecountdown.entity.Event

@Database(entities = arrayOf(Event::class), version = 2)
@TypeConverters(DateTypeConverter::class)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

}
