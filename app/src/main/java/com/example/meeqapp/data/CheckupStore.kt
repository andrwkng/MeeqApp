package com.example.meeqapp.data

import android.content.Context
import android.util.Log
import com.example.meeqapp.ui.checkup.Checkup
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.Date
import javax.inject.Inject

val CHECKUP_KEY_PREFIX = "@Quirk:checkups:"

class CheckupStore @Inject constructor(
    @ApplicationContext appContext: Context,
) {
    private val dataStore = appContext.dataStore

    suspend fun getOrderedCheckups(): List<Checkup> {
        val checkups = getCheckups()

        return checkups.sortedByDescending {
            it.createdAt.let { date -> Date(date as Long).time }
        }
    }

    suspend fun getCheckups(): List<Checkup> {
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