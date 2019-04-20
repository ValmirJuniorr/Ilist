package br.com.valmirjunior.ilist.model.converter

import androidx.room.TypeConverter
import java.util.*


class DateConverter {

    @TypeConverter
    fun fromLong(time: Long?): Date? {
        return  if(time != null) Date(time) else null
    }

    @TypeConverter
    fun toLong(date: Date?): Long? {
        return  date?.time
    }
}