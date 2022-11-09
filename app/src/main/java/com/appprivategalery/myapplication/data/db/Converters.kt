package com.appprivategalery.myapplication.data.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.appprivategalery.myapplication.utils.CalendarUtils
import com.appprivategalery.myapplication.utils.TimeUtils
import java.time.LocalDate
import java.time.LocalTime

class Converters {
    private val calendarUtils = CalendarUtils
    private val timeUtils = TimeUtils

    @TypeConverter
    fun fromLocalDate(localDate: LocalDate) : String{
        return calendarUtils.getStringFromLocalDate(localDate)
    }

    @TypeConverter
    fun toLocalDate(dateString:String) : LocalDate{
        return calendarUtils.getLocalDateFromString(dateString)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalTime(localTime: LocalTime) : String{
        return timeUtils.getStringFromLocalTime(localTime)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalTime(timeString: String) : LocalTime{
        return timeUtils.getLocalTimeFromString(timeString)
    }
}