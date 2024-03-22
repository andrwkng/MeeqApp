
package com.spryteam.meeqapp

import com.spryteam.meeqapp.domain.DateTimeProvider
import com.spryteam.meeqapp.domain.ScheduleFollowUpUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class ScheduleFollowUpUseCaseTest {

    private class TestDateTimeProvider(private val now: LocalDateTime) : DateTimeProvider {
        override fun now(): LocalDateTime {
            return now
        }
    }

    @Test
    fun `schedule follow-up for 7 AM if current time is before 7 AM`() {
        val currentTime = LocalDateTime.of(2024, 3, 5, 5, 0) // Mock current time before 7 AM
        val followUpTime = LocalDateTime.of(2024, 3, 5, 7, 0) // Expected follow-up time tomorrow at 7 AM
        val dateTimeProvider = TestDateTimeProvider(currentTime)

        val useCase = ScheduleFollowUpUseCase(dateTimeProvider)
        val result = useCase()

        assertEquals(followUpTime, result)
    }

    @Test
    fun `schedule follow-up for tomorrow 7 AM if current time is after 9 PM`() {
        val currentTime = LocalDateTime.of(2024, 3, 5, 22, 0) // Mock current time after 9 PM
        val followUpTime = LocalDateTime.of(2024, 3, 6, 7, 0) // Expected follow-up time tomorrow at 7 AM
        val dateTimeProvider = TestDateTimeProvider(currentTime)

        val useCase = ScheduleFollowUpUseCase(dateTimeProvider)
        val result = useCase()

        assertEquals(followUpTime, result)
    }

    @Test
    fun `schedule follow-up for 2 hours current time is between 7 AM and 9 PM`() {
        val currentTime = LocalDateTime.of(2024, 3, 5, 12, 0) // Mock current time between 7 AM and 9 PM
        val followUpTime = LocalDateTime.of(2024, 3, 5, 14, 0) // Expected follow-up time after 2 hours
        val dateTimeProvider = TestDateTimeProvider(currentTime)

        val useCase = ScheduleFollowUpUseCase(dateTimeProvider)
        val result = useCase()

        assertEquals(followUpTime, result)
    }
}
