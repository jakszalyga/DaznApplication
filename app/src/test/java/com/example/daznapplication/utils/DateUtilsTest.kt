package com.example.daznapplication.utils

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class DateUtilsTest {

    private lateinit var currentDateTime: LocalDateTime

    @Before
    fun setUp() {
        currentDateTime = LocalDateTime.of(2023, 1, 15, 12, 0)
    }

    @Test
    fun testTransformDateToday() {
        val date = "2023-01-15T10:00:00Z"
        val result = DateUtils.transformDate(date, currentDateTime)
        assertEquals("Today, 10:00", result)
    }

    @Test
    fun testTransformDateTomorrow() {
        val date = "2023-01-16T10:00:00Z"
        val result = DateUtils.transformDate(date, currentDateTime)
        assertEquals("Tomorrow, 10:00", result)
    }

    @Test
    fun isTomorrowTrue() {
        val date = "2023-01-16T10:00:00Z"
        val result = DateUtils.isTomorrow(date, currentDateTime)
        assertEquals(true, result)
    }

    @Test
    fun isTomorrowFalse() {
        val date = "2023-01-15T10:00:00Z"
        val result = DateUtils.isTomorrow(date, currentDateTime)
        assertEquals(false, result)
    }

}