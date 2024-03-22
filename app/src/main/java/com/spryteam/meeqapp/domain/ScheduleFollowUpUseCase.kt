package com.spryteam.meeqapp.domain

import java.time.LocalDateTime

interface DateTimeProvider {
    fun now(): LocalDateTime
}

class ScheduleFollowUpUseCase(private val dateTimeProvider: DateTimeProvider) {

    operator fun invoke(): LocalDateTime {
        val currentTime = dateTimeProvider.now()
        val inAFewHours = currentTime.plusHours(2)

        // If we're before 7am, then schedule it for 7 am.
        return if (currentTime.hour < 7) {
            LocalDateTime.of(
                currentTime.year,
                currentTime.month,
                currentTime.dayOfMonth,
                7,
                0
            )
        } else if(currentTime.hour > 21) { // If we're after 9pm, then schedule it for tomorrow at 7 am.
            LocalDateTime.of(
                currentTime.year,
                currentTime.month,
                currentTime.dayOfMonth,
                7,
                0
            ).plusDays(1)
        } else {
            inAFewHours
        }

    }
}