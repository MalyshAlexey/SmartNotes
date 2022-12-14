package com.appprivategalery.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "events")
data class Event (
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var name : String?,
    var eventDate : LocalDate?,
    var eventTime : LocalTime?,
    var notificationDate : LocalDate?,
    var notificationTime : LocalTime?,
    var description : String?
        ) : Serializable