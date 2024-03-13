package com.spryteam.meeqapp.data

import android.content.Context
import android.util.Log
import com.spryteam.meeqapp.ui.checkup.Checkup
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Date
import javax.inject.Inject

val CHECKUP_KEY_PREFIX = "@Meeq:checkups:"
const val CHECKUP_SCHEDULE_KEY = "@Meeq:next-checkup-date"

class CheckupStore @Inject constructor(
    @ApplicationContext appContext: Context,
    flagStore: FlagStore
) {
    private val dataStore = appContext.dataStore

    private val getCheckUpScheduleKey: Flow<String?> = flagStore.getFlag(CHECKUP_SCHEDULE_KEY)

    suspend fun getNextCheckupDate(): LocalDate {
        try {
            var date: String? = null
            getCheckUpScheduleKey.collect { date = it }

            if (date.isNullOrEmpty()) {
                // If we haven't had a checkup yet, schedule it for an hour ago
                return LocalDate.now().minus(1, ChronoUnit.HOURS)
            }

            return LocalDate.parse(date)
        } catch (err: Exception) {
            // Next week
            return LocalDate.now().plus(1, ChronoUnit.WEEKS)
        }
    }

    suspend fun getOrderedCheckups(): List<Checkup> {
        val checkups = getCheckups()

        return checkups.sortedByDescending {
            it.createdAt.let { date -> Date(date as Long).time }
        }
    }

    private suspend fun getCheckups(): List<Checkup> {
        try {
            var checkupKeys = listOf<Any>()
            dataStore.data.map { preferences ->
                preferences.asMap().keys.filter { it.toString().startsWith(CHECKUP_KEY_PREFIX) }
            }.collect {
                checkupKeys = it
            }

            val rows = dataStore.data.map { preferences ->
                preferences.asMap()
                    .filterKeys { checkupKeys.contains(it) }
                    .map { Json.decodeFromString<Checkup>(it.value.toString()) }
            }

            // It's better to lose data than to brick the app
            // (though losing data is really bad too)
            return rows.firstOrNull() ?: emptyList()
        } catch (error: Exception) {
            Log.e("getCheckups", "Error retrieving checkups", error)
            return emptyList()
        }
    }

}