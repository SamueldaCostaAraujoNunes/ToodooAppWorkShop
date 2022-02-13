package com.samuelnunes.too_dooapp.data.local.converters

import androidx.room.TypeConverter
import com.samuelnunes.too_dooapp.common.Constants
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeLocalConverter {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(Constants.LOCAL_DATE_TIME_PATTERN)

    @TypeConverter
    fun from(value: LocalDateTime): String = formatter.format(value)

    @TypeConverter
    fun to(value: String): LocalDateTime = LocalDateTime.parse(value, formatter);
}