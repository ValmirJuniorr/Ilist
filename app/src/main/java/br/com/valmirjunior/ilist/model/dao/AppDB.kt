package br.com.valmirjunior.ilist.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.valmirjunior.ilist.model.Event
import br.com.valmirjunior.ilist.model.converter.DateConverter

@Database( entities = [ Event::class ], version = AppDB.VERSION_DB )
@TypeConverters(DateConverter::class)
abstract class AppDB : RoomDatabase() {

    abstract val eventDao: EventDao

    companion object {
        const val VERSION_DB = 2
        const val NAME = "Ilist.db"
    }
}