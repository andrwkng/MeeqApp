package com.example.meeqapp.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.meeqapp.ui.thoughts.SavedThought
import com.example.meeqapp.ui.thoughts.SavedThoughtImpl
import com.example.meeqapp.ui.thoughts.Thought
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.util.Date
import java.util.UUID
import javax.inject.Inject

val THOUGHTS_KEY_PREFIX = "@Meeq:thoughts:";
val EXISTING_USER_KEY = "@Meeq:existing-user";

class ThoughtStore @Inject constructor(
    @ApplicationContext appContext: Context,
    private val flagStore: FlagStore
) {
    private val dataStore = appContext.dataStore
    fun getIsExistingUser(): Flow<Boolean> {
        val key = booleanPreferencesKey(EXISTING_USER_KEY)
        return dataStore.data.map { preferences ->
            preferences[key] ?: false
        }
    }

    suspend fun setIsExistingUser() {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(EXISTING_USER_KEY)] = true
        }
    }

    suspend fun setSeenPredictionOnboarding(bool: Boolean) {
        flagStore.setFlag(Flag.HAS_SEEN_PREDICTION_ONBOARDING, bool)
    }

    suspend fun getOrderedThoughts(): List<SavedThought> {
        val data = getThoughts()
        val thoughts = parseThoughts(data)

        return thoughts.sortedByDescending {
            it.createdAt?.let { date -> Date(date as Long).time } ?: 0
        }
    }

    suspend fun saveThought(thought: Thought): Thought {
        var saveableThought: SavedThought

        val isSavedThought = (thought as? SavedThought)?.uuid == null
        if (isSavedThought) {
            saveableThought = SavedThoughtImpl(
                uuid = getThoughtKey(UUID.randomUUID().toString()),
                createdAt = LocalDate.now(),
                updatedAt = LocalDate.now(),
                alternativeThought = thought.alternativeThought,
                automaticThought = thought.automaticThought,
                challenge = thought.challenge,
                cognitiveDistortions = thought.cognitiveDistortions,
                immediateCheckup = thought.immediateCheckup,
            )
        } else {
            saveableThought = thought as SavedThought
            saveableThought.updatedAt = LocalDate.now()
        }

        return withContext(Dispatchers.IO) {
            try {
                val thoughtString = saveableThought.toString()

                // No matter what, we NEVER save bad data.
                if (thoughtString.isNullOrBlank()) {
                    return@withContext saveableThought
                }

                dataStore.edit { preferences ->
                    preferences[stringPreferencesKey(saveableThought.uuid)] = thoughtString
                }
                return@withContext saveableThought
            } catch (error: Throwable) {
                Log.e("saveThought", "Error saving thought", error)
                return@withContext saveableThought
            }
        }
    }

    suspend fun getThoughts(): List<String> {
        try {
            var keys = listOf<Any>()
            dataStore.data.map { preferences ->
                preferences.asMap().keys.filter { it.toString().startsWith(THOUGHTS_KEY_PREFIX) }
            }.collect{
                keys = it
            }

            val rows = dataStore.data.map { preferences ->
                preferences.asMap()
                    .filterKeys { keys.contains(it) }
                    .map { it.value.toString() }
            }

            // It's better to lose data than to brick the app
            // (though losing data is really bad too)
            return rows.firstOrNull() ?: emptyList()
        } catch (error: Exception) {
            Log.e("getThoughts", "Error retrieving thoughts", error)
            return emptyList()
        }
    }

    suspend fun countThoughts(): Int {
        val exercises = getThoughts()
        return exercises.size
    }
}

fun getThoughtKey(info: String): String {
    return THOUGHTS_KEY_PREFIX + info
}

fun parseThoughts(data: List<String>): List<SavedThought> {
    /*val emptyThought = SavedThoughtImpl(
        createdAt = createdAt,
        updatedAt = updatedAt,
        alternativeThought = thought.alternativeThought,
        automaticThought = thought.automaticThought,
        challenge = thought.challenge,
        cognitiveDistortions = thought.cognitiveDistortions,
        immediateCheckup = thought.immediateCheckup,
        uuid = thought.uuid
    )*/

    return data
        //.map { value -> Json.decodeFromString<SavedThoughtImpl>(if(value.isNullOrEmpty()) value else "{}") } // Worst case scenario, if bad data gets in we don't show it.
        .mapNotNull { value ->
            try {
                Json.decodeFromString<SavedThoughtImpl>(value)
            } catch (e: Exception) {
                null
            }
        }
    //.map(::fixTimestamps)
}

/*fun fixTimestamps(thought: SavedThoughtImpl): SavedThought {


    val createdAt = LocalDate.parse(thought.createdAt.toString())
    val updatedAt = Date(thought.updatedAt as Long)

    return SavedThoughtImpl(
        createdAt = createdAt,
        updatedAt = updatedAt,
        alternativeThought = thought.alternativeThought,
        automaticThought = thought.automaticThought,
        challenge = thought.challenge,
        cognitiveDistortions = thought.cognitiveDistortions,
        immediateCheckup = thought.immediateCheckup,
        uuid = thought.uuid
    )
}*/
