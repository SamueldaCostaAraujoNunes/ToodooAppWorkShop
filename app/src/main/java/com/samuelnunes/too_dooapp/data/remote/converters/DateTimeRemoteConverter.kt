package com.samuelnunes.too_dooapp.data.remote.converters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.samuelnunes.too_dooapp.common.Constants
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeRemoteConverter : TypeAdapter<LocalDateTime>() {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(Constants.REMOTE_DATE_TIME_PATTERN)

    override fun write(out: JsonWriter, value: LocalDateTime) {
        out.value(formatter.format(value))
    }

    override fun read(input: JsonReader): LocalDateTime {
        return LocalDateTime.parse(input.nextString(), formatter);
    }
}