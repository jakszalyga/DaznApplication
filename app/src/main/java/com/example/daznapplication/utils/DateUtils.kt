package com.example.daznapplication.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


object DateUtils {

    fun transformDate(eventDate: String, currentLocalDateTime: LocalDateTime): String {
        val eventDateAsLocalDateTime =
            Instant.parse(eventDate).atZone(ZoneId.of("UTC")).toLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val timeFormatted = eventDateAsLocalDateTime.format(formatter)


        return if (currentLocalDateTime.dayOfYear == eventDateAsLocalDateTime.dayOfYear && currentLocalDateTime.year == eventDateAsLocalDateTime.year) "Today, $timeFormatted"
        else if (currentLocalDateTime.dayOfYear == eventDateAsLocalDateTime.dayOfYear + 1 && currentLocalDateTime.year == eventDateAsLocalDateTime.year) "Yesterday, $timeFormatted"
        else if (currentLocalDateTime.dayOfYear == eventDateAsLocalDateTime.dayOfYear - 1 && currentLocalDateTime.year == eventDateAsLocalDateTime.year) "Tomorrow, $timeFormatted"
        else "${eventDateAsLocalDateTime.year} ${eventDateAsLocalDateTime.month} ${eventDateAsLocalDateTime.dayOfMonth}"

    }

    fun isTomorrow(date: String, now: LocalDateTime): Boolean {
        val dateAsLocalDateTime =
            Instant.parse(date).atZone(ZoneId.systemDefault()).toLocalDateTime()
        if (dateAsLocalDateTime.dayOfYear - 1 == now.dayOfYear && dateAsLocalDateTime.year == now.year) return true
        return false
    }
}